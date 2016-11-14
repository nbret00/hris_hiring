/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    //alert("working_person_id" + working_person_id);
    var working_person_id = "";
    var working_panel = "quickpagebutton";//initially loaded
    var cand_name = "";
    var personProfile;
    var jobQualification;

    $("#searchStr").val("");

    //alert("loaded...")
    //inits
    //1. check authentication
    //2. remove previous search
    //3. prepare screen

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/hris_hiring/webresources/hrisaccount/checkCredential',
        "Access-Control-Allow-Origin: ": "*",
        success: function (data) {
            //alert("done get" + data)
            if (data == "success") {
                //alert("success");
            } else {
                window.location.href = "http://localhost:8080/hris_hiring/index.html?nologin";
            }
        }
    });

    //load the default panel and data
    $("#section1").load("htmlcomponents/quickpage.html", function () {
        //alert("Load was performed.");
        //console.log($("#quickpagebutton").inner);
        document.getElementById("quickpagebutton").innerHTML = "tesstt";
        console.log(document.getElementById("quickpagebutton").innerHTML);
        //console.log($('#section1').find("#quickpage_button").html());
        //initQuickPage("");
        //$("#quickpage_button").value = 'Add New';
    });

    $("#quickpage").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/quickpage.html", function () {

        });
    });
    $("#contact").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/contactInfo.html");
    });

    //search button... key up checking for enter key up only
    $("#searchStr").keyup(function (e) {

        e.preventDefault();
        if (e.which == 13) {
            //alert("keyup enter");
            $("#submitSearch").trigger("click");
        }
    });
    //logout
    $("#logout").submit(function (event) {
//alert("loging out");
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/hris_hiring/webresources/hrisaccount/logout',
            //"Access-Control-Allow-Origin: ": "*",
            success: function (data) {
                if (data == "success") {
                    //alert("going to index");
                    window.location.href = "http://localhost:8080/hris_hiring/index.html?logoutok";
                }
            }
        });
    });
    //checking global variables
    $("#globalvar").click(function (event) {
        console.log("global - name " + cand_name);
        console.log("global - id " + working_person_id);
    });
    //submit of search
    $("#submitSearch").click(function (event) {
        //alert("clicked!");
        event.preventDefault();
        document.getElementById("activePerson").innerHTML = "Searching...";
        searchStr = $("#searchStr").val();
        $("#searchStr").val("");//clear the search field
        cand_name = "";
        working_person_id = "";
        if (isNaN(searchStr)) {
            alert("Rec # should be a number.");
        } else {
            personProfileGet(searchStr);
            prepActivePerson(personProfile);
            prepPersonProfileForm(personProfile);
            showPersonalProfileForm();
        }
    });
    //handlers
    function initQuickPage(id) {
        //if id not set
        alert("initQuickPage");
        if (id == "") {
            //set buttom to add
            alert("no id");
            $("#quickpage_button").value = 'Add New';
        } else {
            alert("id found");
            //load the data from service, set buttom to update  
            $("#quickpage_button").value = 'Update';
        }
    }

    function updateQuickPage() {

    }
    function addQuickPage() {

    }
//----------------------------------------------------- Person Profile
    var create_personalprofile_url = "http://localhost:8080/hris_hiring/webresources/personProfile/save";
    var update_personalprofile_url = "http://localhost:8080/hris_hiring/webresources/person/";
    var get_personProfile_url = "http://localhost:8080/hris_hiring/webresources/person/";

    //nav bars
    $("#canprofile").on("click", function (e) {
        showPersonalProfileForm();
    });

    function showPersonalProfileForm() {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/personalprofile.html", function () {
            if (personProfile == null) {
                console.log("personProfile null");
                document.getElementById("personProfileBut").innerHTML = "Save";
                personProfileSaveUpdateHandler();
            } else {
                document.getElementById("personProfileBut").innerHTML = "Update";
                console.log("PersonProfile not null " + personProfile);
                prepPersonProfileForm(personProfile);
                personProfileSaveUpdateHandler();
            }
        });
    }
    ;

    function prepPersonProfileForm(personXMLdata) {
        console.log("prepPersonProfileform--->>>");

        $(personXMLdata).find("person").each(function () {
            document.getElementById("Name").value = ifnull($(this).find("name").text());
            document.getElementById("LastName").value = ifnull($(this).find("lastName").text());
            document.getElementById("FirstName").value = ifnull($(this).find("firstName").text());
            document.getElementById("DOB").value = ifnull(TimeStampToDate($(this).find("dateOfBirth").text()));
            document.getElementById("Gender").value = ifnull($(this).find("gender").text());
            document.getElementById("personProfileBut").innerHTML = "Update";
        })
        console.log("<<---prepPersonProfileForm");
    }
    ;
    function prepActivePerson(data) {
        cand_name = $(data).find("name").text();
        working_person_id = $(data).find("idPerson").text();
        personProfile = data;
        console.log("Active person: " + cand_name);
        document.getElementById("activePerson").innerHTML = "<h5>Active record: #" + working_person_id + " - " + cand_name + "</h5><a href='home.html'><span class='badge'>Create New Record</span></a>";
    }
    ;
    function personProfileGet(id) {
        $.ajax({
            type: 'GET',
            url: get_personProfile_url + id,
            success: function (data) {
                console.log("person:" + data);
                if (data == null) {
                    alert("No record found for rec#: " + searchStr);
                } else {
                    working_person_id = $(data).find("idPerson").text();
                    personProfile = data; //register to global variable
                }
            },
            error: function (jqXHR, status) {
                alert("Status: " + status);
                alert("Response Text? " + jqXHR.responseText);
            }
        });
    }
    ;
    function personProfileSaveUpdateHandler() {
        $("#personform").submit(function (event) {
            event.preventDefault();
            console.log("clicked " + $("#personProfileBut").text());
            var Person = JSON.stringify({
                name: $('#Name').val(),
                firstName: $('#FirstName').val(),
                lastName: $('#LastName').val(),
                dateOfBirth: new Date($('#DOB').val()),
                gender: $('#Gender').val()
            });

            console.log("This is the text of the button: " + $("#personProfileBut").text());
            if ($("#personProfileBut").text() === "Save") {
                console.log("saving new record person profile.");
                $.ajax({
                    type: 'POST',
                    url: create_personalprofile_url,
                    contentType: 'application/json',
                    data: Person,
                    success: function (data) {
                        personProfile = data;
                        alert("New Record Successfully Created. Record # " + $(data).find("idPerson").text());
                        prepActivePerson(data)
                        prepPersonProfileForm(data);
                    }
                });
            }
            if ($("#personProfileBut").text() === "Update") {
                console.log("updating person profile # " + working_person_id);
                $.ajax({
                    type: 'PUT',
                    url: update_personalprofile_url + working_person_id,
                    contentType: 'application/json',
                    data: Person,
                    success: function (data) {
                        alert("UPDATING PERSON PROFILE!!");
                        personProfile = data;
                        //prepPersonProfileForm(on);
                        //return data;
                    },
                    error: function () {
                        alert("Application Error!");
                    }
                });
            }
        });
    }
    ;
//----------------------------------------------------- Job Qualification
    var get_personProfile_url = "http://localhost:8080/hris_hiring/webresources/person/";
    //var _url = "http://localhost:8080/hris_hiring/webresources/person/";

    $("#jobqualification").on("click", function (e) {
        showQualificationForm();
    });

    function showQualificationForm() {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/jobqualification.html", function () {
            if (jobQualification == null) {
                console.log("personProfile null");
                document.getElementById("jobQualificationBut").innerHTML = "Save";
                //personProfileSaveUpdateHandler();
            } else {
                document.getElementById("jobQualificationBut").innerHTML = "Update";
                console.log("PersonProfile not null " + personProfile);
                //prepPersonProfileForm(personProfile);
                //personProfileSaveUpdateHandler();
            }
        });
    }
    ;
    function jobQualificationGet(id) {
        $.ajax({
            type: 'GET',
            url: get_personProfile_url + id,
            success: function (data) {
                console.log("person:" + data);
                if (data == null) {
                    alert("No record found for rec#: " + searchStr);
                } else {
                    working_person_id = $(data).find("idPerson").text();
                    personProfile = data; //register to global variable
                }
            },
            error: function (jqXHR, status) {
                alert("Status: " + status);
                alert("Response Text? " + jqXHR.responseText);
            }
        });
    }
    ;

//-------------------------start utils

    function ifnull(type) {
        if (type === null) {
            return "";
        } else {
            return type;
        }

    }

    function TimeStampToDate(xmlDate)
    {
        var dt = new Date(xmlDate);
        return (dt.getMonth() + 1) + "/" + dt.getDay() + "/" + dt.getFullYear();
    }




})
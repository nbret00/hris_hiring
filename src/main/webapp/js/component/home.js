/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {


    //alert("working_person_id" + working_person_id);
    var working_person_id = "";
    var cand_name = "";
    var personProfile = null;
    var jobQualification = null;

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
    /*
     $("#searchStr").keyup(function (e) {
     
     e.preventDefault();
     if (e.which == 13) {
     //alert("keyup enter");
     $("#submitSearch").trigger("click");
     }
     });
     */
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
        searchStr = $("#searchStr").val();
        alert("search " + searchStr);
        //if (searchStr != null || searchStr === underfined) {alert("me");}
        if (searchStr !== undefined || searchStr.trim() != "") {
            document.getElementById("activePerson").innerHTML = "Searching...";
            $("#searchStr").val("");//clear the search field
            working_person_id;
            personProfile;
            if (isNaN(searchStr)) {
                alert("Rec # should be a number.");
            } else {
                getPersonalProfile(searchStr, function () {
                    console.log("calledbackeeddd");
                    setTimeout(alert("Searching record # " + searchStr), 10000);
                    showPersonalProfileForm();
                });
            }
            ;
        } else {
            alert("Provide Record # to search.");
        }
        ;
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
            $('.datepicker').datepicker({
                startDate: "09/05/2004"});
            if (personProfile == null) {
                console.log("personPrfile is null");
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
        })
        console.log("<<---prepPersonProfileForm");
    }
    ;
    function prepActivePerson(data) {
        cand_name = $(data).find("name").text();
        working_person_id = $(data).find("idPerson").text();
        console.log("Active person: " + cand_name);
        document.getElementById("activePerson").innerHTML = "<h5>Active record: #" + working_person_id + " - " + cand_name + "</h5><a href='home.html'><span class='badge'>Create New Record</span></a>";
        return true;
    }
    ;
    function getPersonalProfile(id, callback) {
        console.log("------>getPersonalProfile");
        $.ajax({
            type: 'GET',
            url: get_personProfile_url + id,
            success: function (data) {
                console.log("person:" + data);
                if (data == null) {
                    alert("No record found for rec#: " + searchStr);
                } else {
                    working_person_id = $(data).find("idPerson").text();
                    console.log("Found in get Person: " + working_person_id);
                    personProfile = data; //register to global variable
                    prep = prepActivePerson(data);
                }
            },
            error: function (jqXHR, status) {
                alert("Status: " + status);
            }
        });
        if (callback && typeof (callback) === "function") {
            callback();
        }
        ;
        console.log("<------");
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
                        prepActivePerson(data);
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
                        alert("Personal Profile Successfully Updated");
                        getPersonalProfile(working_person_id);
                        prepPersonProfileForm(data);
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
    var get_jobQualification_url = "http://localhost:8080/hris_hiring/webresources/personProfile/jobqualification/";
    var save_jobqualification_url = "http://localhost:8080/hris_hiring/webresources/jobqualification/save/";

    $("#jobqualification").on("click", function (e) {
        if (working_person_id == null || working_person_id == "") {
            alert("Search for a record first or create new profile.");
        } else {
            getJobQualificationByPersonID(working_person_id, function () {
                showQualificationForm();
            });
        }

    });



    function showQualificationForm() {
        console.log("after callback experiment");
        $("#panel").remove();
        $("#section1").load("htmlcomponents/jobqualification.html", function () {
            if (jobQualification == null) {
                console.log("Job Qualification null");
                document.getElementById("jobQualificationBut").innerHTML = "Save";
                jobQualificationSaveUpdateHandler(function () {
                    prepJobQualificationForm(jobQualification);
                });
            } else {
                console.log("Lookup for existing job qualification " + working_person_id);
                prepJobQualificationForm(jobQualification);
                //personProfileSaveUpdateHandler();
            }
        });

    }
    ;

    function prepJobQualificationForm(jobQualificationXMLdata) {
        console.log("prepJobQualificationForm--->>>");
        $(jobQualificationXMLdata).find("jobQualification").each(function () {
            document.getElementById("jobTitle").value = ifnull($(this).find("jobTitle").text());
            document.getElementById("joblevelmo").value = ifnull($(this).find("joblevelmo").text());
            document.getElementById("qualificationSummary").value = ifnull($(this).find("qualificationSummary").text());
            document.getElementById("skillsCategorymo").value = ifnull($(this).find("skillsCategorymo").text());
        })
        document.getElementById("jobQualificationBut").innerHTML = "Update";
        console.log("<<---prepJobQualificationForm");
    }
    ;

    function getJobQualificationByPersonID(id, callback) {
        $.ajax({
            type: 'GET',
            url: get_jobQualification_url + id,
            success: function (data) {
                console.log("job qualification get result:" + data);
                if (data == "noresult") {
                    alert("No record found for rec#: " + searchStr);
                    jobQualification = null;
                } else {
                    jobQualification = data;//global
                }
                if (callback && typeof (callback) === "function") {
                    //do something here from your call back function
                    console.log("Calling the callback inside the function...")
                    callback();
                }
                ;
            },
            error: function (jqXHR, status) {
                alert("Application Error Found: " + status);
            }
        });
    }
    ;

    function jobQualificationSaveUpdateHandler(callback) {
        $("#jobqualificationform").submit(function (event) {
            event.preventDefault();
            console.log("clicked jobQualificationSaveUpdateHandler :" + $("#jobQualificationBut").text());
            var JobQualification = JSON.stringify({
                //jobQualificationPK: $('#jobQualificationPK').val(),
                jobTitle: $('#jobTitle').val(),
                skillsCategorymo: $('#skillsCategorymo').val(),
                joblevelmo: new Date($('#joblevelmo').val()),
                qualificationSummary: $('#qualificationSummary').val(),
                yrsOfExperience: $('#yrsOfExperience').val(),
                currentSalary: $('#currentSalary').val(),
                targetSalary: $('#targetSalary').val(),
                targetPosition: $('#targetPosition').val(),
                priority: $('#priority').val(),
                skills: $('#skills').val(),
                searchText: $('#searchText').val(),
                industriesIdindustries: {
                    idindustries: '1'
                }
                //industryLevelIdindustryLevel: $('#industryLevelIdindustryLevel').val(),
                //payrateIdpayrate: $('#payrateIdpayrate').val()
            });

            console.log("This is the text of the button: " + $("#jobQualificationBut").text());
            if ($("#jobQualificationBut").text() === "Save") {
                console.log("saving new record person profile.");
                $.ajax({
                    type: 'POST',
                    url: save_jobqualification_url + working_person_id,
                    contentType: 'application/json',
                    data: JobQualification,
                    success: function (data) {
                        jobQualification = data;
                        /*
                         if (callback && typeof (callback) === "function") {
                         //do something here from your call back function
                         console.log("Call back SAVE job qualification.");
                         callback();
                         }
                         ;
                         */
                        alert("Job Qualification Successfully Created. - " + $(data).find("jobTitle").text());
                        prepJobQualificationForm(data);

                    }
                });
            }
            if ($("#jobQualificationBut").text() === "Update") {
                console.log("updating person profile # " + working_person_id);
                $.ajax({
                    type: 'PUT',
                    url: update_personalprofile_url + working_person_id,
                    contentType: 'application/json',
                    data: JobQualification,
                    success: function (data) {
                        jobQualification = data;
                        if (callback && typeof (callback) === "function") {
                            //do something here from your call back function
                            console.log("call back UPDATE job qualification");
                            callback();
                        }
                        ;
                        /*
                         alert("Personal Profile Successfully Updated");
                         getPersonalProfile(working_person_id);
                         prepPersonProfileForm(data);
                         */
                    },
                    error: function () {
                        alert("Application Error!");
                    }
                });
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
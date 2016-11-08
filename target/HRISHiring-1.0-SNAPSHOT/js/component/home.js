/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    alert("working_person_id" + working_person_id);
    var working_person_id = "";
    var cand_name = "";
    var personProfile;
    //alert("loaded...")
    //inits
    //1. check authentication
    //2. remove previous search
    //3. prepare screen

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/HRISHiring/webresources/hrisaccount/checkCredential',
        "Access-Control-Allow-Origin: ": "*",
        success: function (data) {
            //alert("done get" + data)
            if (data == "success") {
                //alert("success");
            } else {
                window.location.href = "http://localhost:8080/HRISHiring/index.html?nologin";
            }
        }
    });

    /*
     $.ajax({
     type: 'GET',
     url: 'http://localhost:8080/HRISHiring/webresources/applicant/removeSession',
     success: function (data) {
     //alert("done get" + data)
     if (data == "success") {
     //alert("success");
     } else {
     window.location.href = "http://localhost:8080/HRISHiring/index.html?nologin";
     }
     }
     });
     */

    //load the default panel and data
    $("#section1").load("htmlcomponents/quickpage.html", function () {
        alert("Load was performed.");
        //console.log($("#quickpagebutton").inner);
        document.getElementById("quickpagebutton").innerHTML = "tesstt";
        console.log(document.getElementById("quickpagebutton").innerHTML);
        //console.log($('#section1').find("#quickpage_button").html());
        //initQuickPage("");
        //$("#quickpage_button").value = 'Add New';
    });



    //nav bars
    $("#canprofile").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/personalprofile.html", function () {
            if (personProfile == null) {
                console.log("personProfile null");
            } else {
                console.log("PersonProfile not null " + personProfile);
                prepPersonProfileForm(personProfile);
            }
        });
    });
    $("#quickpage").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/quickpage.html", function () {

        });
    });
    $("#contact").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("contact.html");
    });
    $("#jobqualification").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/jobqualification.html");
    });    


    $("#searchStr").keyup(function (e) {

        e.preventDefault();
        if (e.which == 13) {
            alert("keyup enter");
            $("#submitSearch").trigger("click");
        }
    });

    $("#logout").submit(function (event) {
        //alert("loging out");
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/HRISHiring/webresources/hrisaccount/logout',
            //"Access-Control-Allow-Origin: ": "*",
            success: function (data) {
                if (data == "success") {
                    //alert("going to index");
                    window.location.href = "http://localhost:8080/HRISHiring/index.html?logoutok";
                }
            }
        });
    })

    $("#globalvar").click(function (event) {
        console.log("global - name " + cand_name);
        console.log("global - id " + working_person_id);
    })
    //get only
    $("#submitSearch").click(function (event) {
        //alert("clicked!");
        event.preventDefault();
        document.getElementById("activePerson").innerHTML = "";
        searchStr = $("#searchStr").val();
        cand_name = "";
        working_person_id = "";


        if (isNaN(searchStr)) {
            alert("Application ID should be a number.");
            //$('#Searchresults').pprog.append('..');
            //search for name
        } else {
            alert("number!" + searchStr);
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/HRISHiring/webresources/person/' + searchStr,
                //acontentType: "application/json",
                //dataType: 'json',
                success: function (data) {
                    console.log("person:" + data);
                    personProfile = data;//register to global variable
                    if (data == null) {
                        alert("No record for " + searchStr);
                    } else {
                        $(data).find("person").each(function () {
                            cand_name = $(this).find("name").text(); // 
                            working_person_id = $(this).find("idPerson").text();
                            console.log("person: " + cand_name);
                            document.getElementById("activePerson").innerHTML = "<h4>" + working_person_id + " - " + cand_name + "</h4>";
                        });
                    }
                    /*
                     for (var i = 0; i < data.length; ++i)
                     {
                     $('#Searchresults').append('<br>' + data[i].companyName);
                     }
                     */
                    //alert("Success" + data);
                },
                error: function (jqXHR, status) {
                    alert("Status: " + status);
                    alert("Response Text? " + jqXHR.responseText);
                }
            });
            document.getElementById("activePerson").innerHTML = "<h4>" + working_person_id + " - " + cand_name + "</h4>";
        }


        /*
         $.ajax({
         type: 'GET',
         url: 'http://lowcost-env.f6cahdjuip.ap-southeast-1.elasticbeanstalk.com/webresources/com.nino.app.hrishiring.company/0/1',
         contentType: "application/json",
         dataType: 'json',
         
         */
        /*
         headers: {
         
         Access-Control-Allow-Origin: *,
         "Accept: " : "application/json"
         "Access-Control-Allow-Methods: ": "GET",
         "Access-Control-Allow-Origin: " : "*"
         //"Access-Control-Allow-Headers: ": "Authorization",
         },
         */
        /*
         
         */

    });

    //handlerssssssssss
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
    function prepPersonProfileForm(personXMLdata) {

        $(personXMLdata).find("person").each(function () {
            document.getElementById("name").value = ifnull($(this).find("name").text());
            document.getElementById("lastName").value = ifnull($(this).find("lastName").text());
            document.getElementById("firstName").value = ifnull($(this).find("firstName").text());
            document.getElementById("dateOfBirth").value = ifnull(TimeStampToDate($(this).find("dateOfBirth").text()));
            document.getElementById("gender").value = ifnull($(this).find("gender").text());
            document.getElementById("personProfileBut").innerHTML = "Update";
        })
    };

    $("#personform").submit(function(event){
        event.preventDefault();
        console("clicked"+$(this).value);
    });
    
//----------------------------------------------------- Person Profile END

    function ifnull(type) {
        if (type == null) {
            return "";
        } else {
            return type;
        }

    }
    
    function TimeStampToDate(xmlDate)
    {
        var dt = new Date(xmlDate);
        return dt.getMonth()+"-"+dt.getDay()+"-"+dt.getFullYear();
    }    




})
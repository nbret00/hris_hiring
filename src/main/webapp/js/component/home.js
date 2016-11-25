

$(document).ready(function () {

    /*
     console.log("document.URL : "+document.URL);
     console.log("document.location.href : "+document.location.href);
     console.log("document.location.origin : "+document.location.origin);
     console.log("document.location.hostname : "+document.location.hostname);
     console.log("document.location.host : "+document.location.host);
     console.log("document.location.pathname : "+document.location.pathname);
     console.log("location.hostname: "+location.hostname);
     console.log("document.domain: "+document.domain);
     console.log("window.location.hostname: "+window.location.hostname);
     //alert("working_person_id" + working_person_id);
     */

    var working_person_id = "";
    var working_jobqualification_id = "";
    var working_contact_id = "";
    var working_sourcing_id = "";
    var cand_name = "";
    var personProfile = null;
    var jobQualification = null;
    var contactInfo = null;
    var activities = null;
    var sourcing = null;

    var searchStr = null;


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
    $("#section1").load("htmlcomponents/personalprofile.html", function () {
        document.getElementById("personProfileBut").innerHTML = "Save";
        personProfileSaveUpdateHandler();
    });

    $("#quickpage").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/quickpage.html", function () {

        });
    });

    $("#logout").submit(function (event) {
        event.preventDefault();
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
    $("#searchForm").submit(function (event) {
        //alert("clicked!");
        event.preventDefault();
        searchStr = null;
        searchStr = document.getElementById("searchStr").value;//$("#searchStr").val();
        console.log("serching for: " + searchStr);

        if (searchStr !== null) {
            //refreshing all global variable

            $("#searchStr").val("");//clear the search field
            working_person_id = "";
            working_jobqualification_id = "";
            working_contact_id = "";
            working_sourcing_id = "";
            cand_name = "";
            personProfile = null;
            jobQualification = null;
            contactInfo = null;
            activities = null;
            sourcing = null;

            if (isNaN(searchStr)) {
                showAlert("Rec # should be a number.");
            } else {
                showLoader();
                //document.getElementById("activePerson").innerHTML = "Searching...";
                getPersonalProfile(searchStr, function () {
                    //setTimeout(alert("Searching record # " + searchStr), 10000);
                    showPersonalProfileForm();
                });
            }
            ;
        } else {
            showAlert("Provide Record # to search.");
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


//----------------------------------------------------- Person Profile
    var create_personalprofile_url = "http://localhost:8080/hris_hiring/webresources/personProfile/save";
    var update_personalprofile_url = "http://localhost:8080/hris_hiring/webresources/person/";
    var get_personProfile_url = "http://localhost:8080/hris_hiring/webresources/person/";

    //nav bars
    $("#canprofile").on("click", function (e) {
        showLoader();
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
                hideLoader();
            } else {
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
        //document.getElementById("loader_comp"). setAttribute("class","loader-sm overlay_center");
        hideLoader();
        console.log("<<---prepPersonProfileForm");
    }
    ;
    function prepActivePerson(data, callback) {
        cand_name = $(data).find("name").text();
        working_person_id = $(data).find("idPerson").text();
        console.log("Active person: " + cand_name);
        document.getElementById("activePerson").innerHTML = "<h5>You're currenty working on candidate #</h5><h3>(" +
                working_person_id + ") " + cand_name +
                "</h3><a href='home.html' class='btn btn-default btn-sm'>Enter new Candidate</a> " +
                " <a class='btn btn-default btn-sm' href='#' id='activities-but'>Hiring Activities</a>";

        if (callback && typeof (callback) === "function") {
            //do something here from your call back function
            console.log("Calling the callback inside the function...")
            callback();
        }
        ;
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
                    showAlert("No record found for rec#: " + searchStr);
                } else {
                    working_person_id = $(data).find("idPerson").text();
                    console.log("Found in get Person: " + working_person_id);
                    personProfile = data; //register to global variable
                    prepActivePerson(data, function () {
                        activityButEvent();//register button event
                    });
                }
                if (callback && typeof (callback) === "function") {
                    console.log("calling callback function from getPerson");
                    callback();
                }
                ;
            },
            error: function (jqXHR, status) {
                showAlert("Error message: " + status);
            }
        });

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
                        showAlert("New Record Successfully Created. Record # " + $(data).find("idPerson").text());
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
                        showAlert("Personal Profile Successfully Updated");
                        getPersonalProfile(working_person_id);
                        prepPersonProfileForm(data);
                    },
                    error: function () {
                        showAlert("Application Error! Please contact system admin.");
                    }
                });
            }
        });

    }
    ;
//----------------------------------------------------- Job Qualification
    var get_jobQualification_url = "http://localhost:8080/hris_hiring/webresources/jobqualification/";
    var save_jobqualification_url = "http://localhost:8080/hris_hiring/webresources/jobqualification/save/";
    var update_jobqualification_url = "http://localhost:8080/hris_hiring/webresources/jobqualification/";

    $("#jobqualification").on("click", function (e) {
        if (working_person_id == null || working_person_id == "") {
            showAlert("Search for a record first to associate Job Qualification or create new profile.");
        } else {
            getJobQualificationByPersonID(function () {
                showQualificationForm();
            });
        }
    });



    function showQualificationForm() {
        console.log("showQualificationForm() called.....");
        $("#panel").remove();
        $("#section1").load("htmlcomponents/jobqualification.html", function () {
            if (jobQualification == null) {
                console.log("Job Qualification null");
                document.getElementById("jobQualificationBut").innerHTML = "Save";
                jobQualificationSaveUpdateHandler();
            } else {
                console.log("Lookup for existing job qualification " + working_person_id);
                prepJobQualificationForm(jobQualification);
                jobQualificationSaveUpdateHandler();
            }
        });

    }
    ;

    function prepJobQualificationForm(jobQualificationXMLdata) {
        console.log("prepJobQualificationForm--->>>");
        $(jobQualificationXMLdata).find("jobQualification").each(function () {
            //alert($(this).find("industryLevelIdindustryLevel").text());
            document.getElementById("jobTitle").value = ifnull($(this).find("jobTitle").text());
            document.getElementById("yrsOfExperience").value = ifnull($(this).find("yrsOfExperience").text());
            document.getElementById("currentSalary").value = ifnull($(this).find("currentSalary").text());
            document.getElementById("targetSalary").value = ifnull($(this).find("targetSalary").text());
            document.getElementById("targetPosition").value = ifnull($(this).find("targetPosition").text());
            document.getElementById("skillsCategorymo").value = ifnull($(this).find("skillsCategorymo").text());
            document.getElementById("joblevelmo").value = ifnull($(this).find("joblevelmo").text());
            document.getElementById("priority").value = ifnull($(this).find("priority").text());
            document.getElementById("industriesIdindustries").value = ifnull($(this).find("industriesIdindustries").find("idindustries").text());
            document.getElementById("industryLevelIdindustryLevel").value = ifnull($(this).find("industryLevelIdindustryLevel").find("idindustryLevel").text());
            document.getElementById("payrateIdpayrate").value = ifnull($(this).find("payrateIdpayrate").find("idpayrate").text());
            document.getElementById("qualificationSummary").value = ifnull($(this).find("qualificationSummary").text());
            document.getElementById("skills").value = ifnull($(this).find("skills").text());
            document.getElementById("searchText").value = ifnull($(this).find("searchText").text());
        });
        document.getElementById("jobQualificationBut").innerHTML = "Update";
        console.log("<<---prepJobQualificationForm");
    }
    ;

    function getJobQualificationByPersonID(callback) {
        $.ajax({
            type: 'GET',
            url: get_jobQualification_url + working_person_id,
            success: function (data) {
                console.log("job qualification get result:" + data);
                if (data == "noresult") {
                    //showAlert("Creat: " + working_person_id);
                    jobQualification = null;
                    working_jobqualification_id = "";
                } else {
                    jobQualification = data;//global
                    working_jobqualification_id = $(data).find("jobQualification").find("idJobQualification").text();
                    //alert("job qualification pk: "+working_jobqualification_id);
                }
                if (callback && typeof (callback) === "function") {
                    //do something here from your call back function
                    console.log("Calling the callback inside the function...")
                    callback();
                }
                ;
            },
            error: function (jqXHR, status) {
                showAlert("Application Error Found: " + status);
            }
        });
    }
    ;

    function jobQualificationSaveUpdateHandler() {
        $("#jobqualificationform").submit(function (event) {
            event.preventDefault();
            console.log("clicked jobQualificationSaveUpdateHandler :" + $("#jobQualificationBut").text());
            var JobQualificationData = JSON.stringify({
                idJobQualification: working_jobqualification_id,
                jobTitle: $('#jobTitle').val(),
                skillsCategorymo: $('#skillsCategorymo').val(),
                joblevelmo: $('#joblevelmo').val(),
                qualificationSummary: $('#qualificationSummary').val(),
                yrsOfExperience: $('#yrsOfExperience').val(),
                currentSalary: $('#currentSalary').val(),
                targetSalary: $('#targetSalary').val(),
                targetPosition: $('#targetPosition').val(),
                priority: $('#priority').val(),
                skills: $('#skills').val(),
                searchText: $('#searchText').val(),
                industriesIdindustries: {idindustries: document.getElementById("industriesIdindustries").value},
                industryLevelIdindustryLevel: {idindustryLevel: $('#industryLevelIdindustryLevel').val()},
                payrateIdpayrate: {idpayrate: $('#payrateIdpayrate').val()}
            });

            console.log("This is the text of the button: " + JobQualificationData.toString());
            if ($("#jobQualificationBut").text() === "Save") {
                console.log("saving new record person profile.");
                $.ajax({
                    type: 'POST',
                    url: save_jobqualification_url + working_person_id,
                    contentType: 'application/json',
                    data: JobQualificationData,
                    success: function (data) {
                        jobQualification = data;
                        showAlert("Job Qualification Successfully Created. - " + $(data).find("jobTitle").text());
                        prepJobQualificationForm(data);
                    }
                });
            }
            if ($("#jobQualificationBut").text() === "Update") {
                console.log("updating job qualification with ID # " + working_jobqualification_id);
                //JobQualificationData["jobQualificationPK"] = $(jobQualification).find("jobQualification").find("idJobQualification").text();
                $.ajax({
                    type: 'PUT',
                    url: update_jobqualification_url + working_person_id,
                    contentType: 'application/json',
                    data: JobQualificationData,
                    success: function (data) {
                        jobQualification = data;
                        showAlert("Successfully updated Job Qualification.")
                    },
                    error: function () {
                        showAlert("Application Error!");
                    }
                });
            }
        });
    }
    ;

//-------------------------contacts

    var get_contact_url = "http://localhost:8080/hris_hiring/webresources/contact/";
    var save_contact_url = "http://localhost:8080/hris_hiring/webresources/contact/save/";
    var update_contact_url = "http://localhost:8080/hris_hiring/webresources/contact/";

    $("#contact").on("click", function (e) {
        if (working_person_id == null || working_person_id == "") {
            showAlert("Search for a record first to associate this contact or create new profile.");
        } else {
            getContactByPersonID(function () {
                showContactForm();
            });
        }
    });
    function showContactForm() {
        console.log("showContactForm() called.....");
        $("#panel").remove();
        $("#section1").load("htmlcomponents/contactInfo.html", function () {
            if (contactInfo == null) {
                console.log("contactInfo null");
                document.getElementById("contactBut").innerHTML = "Save";
                contactSaveUpdateHandler();
            } else {
                console.log("Lookup for existing conract " + working_person_id);
                prepContactForm(contactInfo);
                contactSaveUpdateHandler();
            }
        });

    }
    ;

    function prepContactForm(contactInfoXMLdata) {
        console.log("prepContactForm--->>>");
        $(contactInfoXMLdata).find("contact").each(function () {
            //showAlert($(this).find("contactNum").text());
            document.getElementById("contactNum").value = ifnull($(this).find("contactNum").text());
            document.getElementById("cellphoneNum").value = ifnull($(this).find("cellphoneNum").text());
            document.getElementById("email").value = ifnull($(this).find("email").text());
            document.getElementById("address").value = ifnull($(this).find("address").text());
            document.getElementById("city").value = ifnull($(this).find("city").text());
            document.getElementById("country").value = ifnull($(this).find("country").text());
        });
        document.getElementById("contactBut").innerHTML = "Update";
        console.log("<<---prepContactForm");
    }
    ;

    function getContactByPersonID(callback) {
        $.ajax({
            type: 'GET',
            url: get_contact_url + working_person_id,
            success: function (data) {
                console.log("contact get result:" + data);
                if (data == "noresult") {
                    //showAlert("No record found for rec#: " + working_person_id);
                    contactInfo = null;
                    working_contact_id = "";
                } else {
                    contactInfo = data;//global
                    working_contact_id = $(data).find("contact").find("idcontact").text();
                    //showAlert("contact pk: " + working_contact_id);
                }
                if (callback && typeof (callback) === "function") {
                    //do something here from your call back function
                    console.log("Calling the callback inside the function...")
                    callback();
                }
                ;
            },
            error: function (jqXHR, status) {
                showAlert("Application Error Found: " + status);
            }
        });
    }
    ;

    function contactSaveUpdateHandler() {
        $("#contactForm").submit(function (event) {
            event.preventDefault();
            console.log("clicked contactSaveUpdateHandler :" + $("#contactBut").text());
            var ContactData = JSON.stringify({
                idcontact: working_contact_id,
                telNum1: $('#jobTitle').val(),
                contactNum: $('#contactNum').val(),
                cellphoneNum: $('#cellphoneNum').val(),
                email: $('#email').val(),
                address: $('#address').val(),
                city: $('#city').val(),
                country: $('#country').val(),
            });

            console.log("This is the text of the button111: " + ContactData.toString());
            if ($("#contactBut").text() == "Save") {
                console.log("saving new contact.");
                $.ajax({
                    type: 'POST',
                    url: save_contact_url + working_person_id,
                    contentType: 'application/json',
                    data: ContactData,
                    success: function (data) {
                        countryInfo = data;
                        showAlert("Contact Successfully Created. - " + $(data).find("email").text());
                        prepContactForm(data);
                    }
                });
            }
            if ($("#contactBut").text() == "Update") {
                console.log("updating person profile # " + working_person_id);
                //JobQualificationData["jobQualificationPK"] = rea$(jobQualification).find("jobQualification").find("idJobQualification").text();
                $.ajax({
                    type: 'PUT',
                    url: update_contact_url + working_person_id,
                    contentType: 'application/json',
                    data: ContactData,
                    success: function (data) {

                        console.log("update successfull");
                        contactInfo = data;
                        showAlert("You have just successfully updated the contact.");

                    },
                    error: function () {
                        showAlert("Application Error!");
                    }
                });
            }
        });
    }
    ;
//------------------------- activities


    var get_activities_url = "http://localhost:8080/hris_hiring/webresources/activities/act/";
    var save_activities_url = "http://localhost:8080/hris_hiring/webresources/sourcing/save";
    var update_activities_url = "http://localhost:8080/hris_hiring/webresources/sourcing/update/";

    function activityButEvent() {
        $("#activities-but").on("click", function (e) {
            //function activitiesBadgeOnclick(){
            console.log("activities-badge clicked");
            if (working_person_id == null || working_person_id == "") {
                showAlert("Search for a record first to view activities.");
            } else {
                getActivities(function () {
                    //setTimeout(alert("Searching record # " + searchStr), 100005
                    console.log("activities callback");

                    //console.log("sourcing native: " + sourcing + "-sourcing ID: " + working_sourcing_id);
                    showActivityForm();
                });
            }
        });
    }
    function showActivityForm(callback) {
        //console.log(working_sourcing_id + "() called....." + $(sourcing).html());
        console.log("working activity form");
        $("#panel").remove();
        $("#section1").load("htmlcomponents/activities.html", function () {

            //console.log("data: "+$(activities).find(nsbActivitiess));
            var activityContainer = $("<div class='col-md-12' >");

            $(activities).find("nsbActivities").each(function () {
                console.log("activity description" + $(this).find("description").text());
                var composedHtml = "<div class='panel panel-default'>" +
                        "<div class='panel-heading'>Activity Type - " + $(this).find("nsbActivityTp").find("name").text() + "</div>" +
                        "<div class='panel-body'><strong>Activity ID: </strong>" + $(this).find("idSourcingActivities").text() + "<br/>" +
                        "<strong>Status :</strong> " + $(this).find("nsbActivityStatusTp").find("name").text() + "</br>" +
                        "<strong>Description: </strong> " + $(this).find("description").text() + "</div>" +
                        "<div class='panel-footer'><div class='row'><div class='col-xs-9'><small>Date Created: " + TimeStampToDate($(this).find("createdDt").text()) +
                        " / Created By: " + $(this).find("createdBy").text() + "</small></div>" +
                        "<div class='col-xs-3 btn-group btn-group-xs' role='group'><button type='button' class='btn btn-default'>Remarks</button><button type='button' class='btn btn-default'>Update</button></div></div></div></div>";

                activityContainer.append(composedHtml);
            })
            activityContainer.append("</div>");
            console.log("html to add:" + activityContainer.html());
            $("#activityData").append(activityContainer);
        });
        if (callback && typeof (callback) === "function") {
            //do something here from your call back function
            console.log("Calling the callback inside the function...")
            callback();
        }
        ;
    }
    ;
    function getActivities(callback) {
        $.ajax({
            type: 'GET',
            url: get_activities_url + working_person_id,
            success: function (data) {
                console.log("activites get result:" + data);
                if (data == "noresult") {
                    showAlert("Warning! No activity found for this candidate: " + working_person_id);
                } else {
                    activities = data;
                }
                if (callback && typeof (callback) === "function") {
                    //do something here from your call back function
                    console.log("Calling the callback inside the function getActivities...")
                    callback();
                }
                ;
            },
            error: function (jqXHR, status) {
                showAlert("Application Error Found: " + status);
            }
        });
    }
    ;
    /*could be use for update later
     function prepSourcingForm() {
     console.log("prepSourcingForm--->>>");
     $(personProfile).find("sourcingIdsourcingCampaigne").each(function () {
     //alert($(this).find("contactNum").text());
     document.getElementById("title").value = ifnull($(this).find("title").text());
     document.getElementById("status").value = ifnull($(this).find("status").text());
     document.getElementById("targetJobTitle").value = ifnull($(this).find("targetJobTitle").text());
     document.getElementById("targetJobCategory").value = ifnull($(this).find("targetJobCategory").text());
     document.getElementById("dateContacted").value = ifnull($(this).find("dateContacted").text());
     document.getElementById("source").value = ifnull($(this).find("source").text());
     document.getElementById("sourcer").value = ifnull($(this).find("sourcer").text());
     document.getElementById("contactedBy").value = ifnull($(this).find("contactedBy").text());
     document.getElementById("interviewer").value = ifnull($(this).find("interviewer").text());
     document.getElementById("dateOfInterview").value = ifnull($(this).find("dateOfInterview").text());
     document.getElementById("comments").value = ifnull($(this).find("comments").text());
     });
     document.getElementById("sourcingBut").innerHTML = "Update";
     console.log("<<---prepSourcingForm");
     }
     ;
     */
    function getActivityFormData(){
            var activityData = JSON.stringify({
                idsourcingCampaigne: working_sourcing_id,
                title: $('#title').val(),
                status: $('#comments').val(),
                targetJobTitle: $('#targetJobTitle').val(),
                targetJobCategory: $('#targetJobCategory').val(),
                dateContacted: $('#dateContacted').val(),
                source: $('#source').val(),
                sourcer: $('#sourcer').val(),
                contactedBy: $('#contactedBy').val(),
                interviewer: $('#interviewer').val(),
                dateOfInterview: $('#dateOfInterview').val(),
                moDateAcceptedInLinkedin: $('#moDateAcceptedInLinkedin').val(),
                comments: $('#comments').val()
            });
            return activityData;
    }

    function activitySaveHandler() {
        $("#sourcingForm").submit(function (event) {
            event.preventDefault();
            console.log("clicked contactSaveUpdateHandler :" + $("#sourcingBut").text());
            //activityData = getActivityFormData();
            console.log("saving new contact.");
            $.ajax({
                type: 'POST',
                url: save_sourcing_url,
                contentType: 'application/json',
                data: getActivityFormData(),
                success: function (data) {
                    sourcing = data;
                    working_sourcing_id = $(data).find("idsourcingCampaigne").text();
                    alert("Sourcing Campaign Successfully Created. - " + $(data).find("title").text());
                    prepSourcingForm();
                }
            });
        })
    }
    function updateActivityHandler(){
        $("#sourcingForm").submit(function (event) {
            if ($("#sourcingBut").text() == "Update") {
                console.log("updating sourcing record # " + working_sourcing_id);
                //JobQualificationData["jobQualificationPK"] = $(jobQualification).find("jobQualification").find("idJobQualification").text();
                $.ajax({
                    type: 'PUT',
                    url: update_sourcing_url + working_person_id,
                    contentType: 'application/json',
                    data: SourcingData,
                    success: function (data) {
                        console.log("update successfull");
                        sourcing = data;
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

    function showLoader() {
        $("#loader_comp").addClass("loader-sm");
        $("#loader_comp").addClass("overlay_center");
        $("#section1").addClass("disableddiv");
    }

    function hideLoader() {
        $("#loader_comp").removeClass("loader-sm");
        $("#loader_comp").removeClass("overlay_center");
        $("#section1").removeClass("disableddiv");
    }

    function showAlert(msg) {
        $("#alert-msg").html("<p>" + msg + "</p>");
        $("#myModalalert").modal();
    }



})
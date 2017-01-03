
//register global variable for sharing data between components
var get_personProfile_url = "http://localhost:8080/hris_hiring/webresources/person/";
var get_contact_url = "http://localhost:8080/hris_hiring/webresources/contact/";
var get_jobQualification_url = "http://localhost:8080/hris_hiring/webresources/jobqualification/";
var get_activities_url = "http://localhost:8080/hris_hiring/webresources/activities/act/";
var get_activity_status_tp = "http://localhost:8080/hris_hiring/webresources/nsbactivitystatustp";
var get_activity_tp = "http://localhost:8080/hris_hiring/webresources/nsbactivitytp";
var save_activities_url = "http://localhost:8080/hris_hiring/webresources/activities/save";
var update_activities_url = "http://localhost:8080/hris_hiring/webresources/sourcing/update/";

var working_jobqualification_id = "";
var jobQualification = null;

var personProfile = null;
var working_person_id = "";

var credentialID = "";
var credential = null;

var contactInfo = null;
var working_contact_id = "";

var searchNamesForm_fname = "";
var searchNamesForm_lname = "";

var activities = null;
var working_activity_id = "";

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


    var working_sourcing_id = "";
    var cand_name = "";

    var sourcing = null;
    var searchStr = null;



    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/hris_hiring/webresources/hrisaccount/checkCredential',
        "Access-Control-Allow-Origin: ": "*",
        success: function (data) {
            //alert("done get" + data)
            if (data != null) {
                //alert("success");
                credential = data;
                credentialID = $(data).find("credential").find("accountID").text();
                console.log("credential data -" + credentialID);
                if (credentialID == "") {
                    window.location.href = "http://localhost:8080/hris_hiring/index.html?nologin";
                } else {
                    var recid = GetURLParameter("recid");
                    console.log("person id = " + recid);
                    if (recid !== undefined) {
                        getPersonalProfile(recid, function () {
                            showPersonalProfileForm();
                            prepActivePerson(personProfile);
                        });
                    }
                }
            } else {
                window.location.href = "http://localhost:8080/hris_hiring/index.html?nologin";
            }
        },
        error: function () {
            setTimeout(showAlert("The application found problem on your credential, please contact administrator."), 3000);
            window.location.href = "http://localhost:8080/hris_hiring/index.html?nologin";
        }
    });
    //load the default panel and data
    $("#section1").load("htmlcomponents/createNewCheckDup.html", function () {
        $.getScript("js/component/createNewCheckDup.js");
    });
    $("#generalinfopage").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/generalInformation.html", function () {
            $.getScript("js/component/generalInformation.js");
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
        searchStr = document.getElementById("searchStr").value; //$("#searchStr").val();
        console.log("serching for: |" + searchStr + "|");
        if (searchStr === null || searchStr == '') {
            showAlert("Provide Record # to search.");
            //refreshing all global variable
        } else {

            $("#searchStr").val(""); //clear the search field
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
                    prepActivePerson(personProfile);

                });
            }
            ;
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
                "</h3><a href='home.html' class='btn btn-default btn-sm'>Enter new Candidate</a> ";
        if (callback && typeof (callback) === "function") {
            //do something here from your call back function
            console.log("Calling the callback inside the function...");
            callback();
        }
        ;
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



    var get_remarks_url = "http://localhost:8080/hris_hiring/webresources/activities/remarks/";
    var add_remark_url = "http://localhost:8080/hris_hiring/webresources/activities/remarks/add";
    var get_activity_tp = "http://localhost:8080/hris_hiring/webresources/nsbactivitytp";

    $("#activities-but").on("click", function (e) {
        if (working_person_id == null || working_person_id == "") {
            //showAlert("Search for a record first to view activities.");
        } else {
            $("#panel_activity").remove();
            //$("#activities-but").attr("class","active");
            //$("#remarks-but").attr("class","");
            $("#rem_container").load("htmlcomponents/activities.html", function () {
                $.getScript("js/component/activities.js");
            });
        }
    });

    /* No remarks on activities. This snippet could still be useful.
     function showRemarks(activityID, callback) {
     getActivityRemarks(activityID, function (data) {
     var dom_select_act = "div[data-act-id=\'" + activityID + "\']";
     var remarks_dom = $(dom_select_act).find("#remarks_row").clone();
     $(dom_select_act).find("#remarks_row").remove();
     $(data).find("nsbRemarks").each(function () {
     console.log("remarks: " + $(this).find("remarks").text());
     var i_remarks_dom = remarks_dom.clone();
     i_remarks_dom.find("#remarks_body").text($(this).find("remarks").text());
     $(dom_select_act).find("#remarks").append(i_remarks_dom);
     });
     if (callback && typeof (callback) === "function") {
     //do something here from your call back function
     console.log("Calling the callback inside the function getActivities...")
     callback();
     }
     })
     }
     
     function getActivityRemarks(activity_id, callback) {
     console.log("URL: " + get_remarks_url + activity_id);
     $.ajax({
     type: 'GET',
     url: get_remarks_url + activity_id,
     success: function (data) {
     console.log("data from getActivityRemarks: " + $(data).html());
     if (callback && typeof (callback) === "function") {
     //do something here from your call back function
     console.log("Calling the callback inside the function getActivities...")
     callback(data);
     }
     ;
     },
     error: function (jqXHR, status) {
     showAlert("Application Error Found: " + status);
     }
     });
     }
     
     
     function getRemarksFormData() {
     var remarksData = JSON.stringify({
     remarks: credentialID,
     nsbactivitiesidSourcingActivities: {idSourcingActivities: '1'},
     });
     return remarksData;
     }
     
     function addRemarks(callback) {
     console.log("clicked activities save handler");
     $("#activityform").submit(function (event) {
     event.preventDefault();
     console.log("clicked contactSaveUpdateHandler :" + $("#sourcingBut").text());
     //activityData = getActivityFormData();
     console.log("saving new contact.");
     $.ajax({
     type: 'POST',
     url: add_remark_url,
     contentType: 'application/json',
     data: getRemarksFormData(),
     success: function (data) {
     getActivities(function () {
     showActivityForm();
     });
     
     if (callback && typeof (callback) === "function") {
     //do something here from your call back function
     console.log("Calling the callback inside the function getActivities...")
     callback(data);
     }
     ;
     }
     });
     })
     }
     */



//this is not working yet TODO
    function changeActivityStatusHandler() {
        $("[id^=act_status]").on("change", "select", function () {
            showAlert("clicked!!!!!!" + $(this).attr("id").val());
        })
    }


});

function getActivities(callback) {
    $.ajax({
        type: 'GET',
        url: get_activities_url + working_person_id,
        success: function (data) {
            console.log("activites get result:" + data);
            if (data == "noresult") {
                //showAlert("Warning! No activity found for this candidate: " + working_person_id);
            } else {
                activities = data;
            }
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                console.log("Calling the callback inside the function getActivities...")
                callback(data);
            }
            ;
        },
        error: function (jqXHR, status) {
            showAlert("Application Error Found: " + status);
        }
    });
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
                jobQualification = data; //global
                working_jobqualification_id = $(data).find("jobQualification").find("idJobQualification").text();
                //alert("job qualification pk: "+working_jobqualification_id);
            }
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                console.log("Calling the callback inside the function...");
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
// this are shared functions
function getContactByPersonID(callback) {
    $.ajax({
        type: 'GET',
        url: get_contact_url + working_person_id,
        success: function (data) {
            if (data == "noresult") {
                //showAlert("No record found for rec#: " + working_person_id);
                contactInfo = null;
                working_contact_id = "";
            } else {
                contactInfo = data; //global
                working_contact_id = $(data).find("contact").find("idcontact").text();
                //showAlert("contact pk: " + working_contact_id);
            }
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                console.log("Calling the callback inside the function...");
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

function ifnull(type) {
    if (type === null) {
        return "";
    } else {
        return type;
    }

}

function showAlert(msg) {
    $("#alert-msg").html("<p>" + msg + "</p>");
    $("#myModalalert").modal();
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
function TimeStampToDate(xmlDate)
{
    var dt = new Date(xmlDate);
    return (dt.getMonth() + 1) + "/" + dt.getDay() + "/" + dt.getFullYear();
}
function GetURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam)
        {
            return sParameterName[1];
        }
    }
}

function lookupSelectValue(url, selinput, objname, opt_id_dom, name_dom, active_id, callback) {

    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {

            if (selinput != null) {

                $(data).find(objname).each(function () {
                    var opt_text = $(this).find(name_dom).text();
                    var opt_id = $(this).find(opt_id_dom).text();
                    if (active_id == opt_id) {
                        $(selinput).append("<option selected='selected' value='" + opt_id + "'>" + opt_text + "</option>");
                    } else {
                        $(selinput).append("<option value='" + opt_id + "'>" + opt_text + "</option>");
                    }
                });
            }
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                //console.log("Calling the callback inside the function getActivities...")
                callback(data);
            }
            ;
        },
        error: function (jqXHR, status) {
            showAlert("Application Error encountered in getFramework: " + status);
        }
    });
}
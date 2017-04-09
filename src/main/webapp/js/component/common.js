
//register global variable for sharing data between components

var url_origin = document.location.origin;

var get_personProfile_url = url_origin + "/hris_hiring/webresources/person/";
var update_personalprofile_url = url_origin + "/hris_hiring/webresources/person/";
var create_personalprofile_url = url_origin + "/hris_hiring/webresources/personProfile/save";

var get_contact_url = url_origin + "/hris_hiring/webresources/contact/";
var update_contact_url = url_origin + "/hris_hiring/webresources/contact/";
var save_contact_url = url_origin + "/hris_hiring/webresources/contact/save/";

var get_jobQualification_url = url_origin + "/hris_hiring/webresources/jobqualification/";
var save_jobqualification_url = url_origin + "/hris_hiring/webresources/jobqualification/save/";
var update_jobqualification_url = url_origin + "/hris_hiring/webresources/jobqualification/";

var get_activities_url = url_origin + "/hris_hiring/webresources/activities/act/";
var get_activity_status_tp = url_origin + "/hris_hiring/webresources/nsbactivitystatustp";
var get_activity_tp = url_origin + "/hris_hiring/webresources/nsbactivitytp";
var save_activities_url = url_origin + "/hris_hiring/webresources/activities/save";
var update_activities_url = url_origin + "/hris_hiring/webresources/activities/update";//put
var get_activityEntity_url = url_origin + "/hris_hiring/webresources/activities/activityEntity/";

var get_remarks_url = url_origin + "/hris_hiring/webresources/activities/remarksByPerson/";
var add_remarks_url = url_origin + "/hris_hiring/webresources/activities/remarks/add";

var get_credential_url = url_origin + "/hris_hiring/webresources/hrisaccount/checkCredential";

//searches
var url_searchByNames = url_origin + "/hris_hiring/webresources/jobqualification/searchFirstname/";
var url_searchByFullName = url_origin + "/hris_hiring/webresources/personProfile/searchByName/";
var url_searchByPersonNames = url_origin + "/hris_hiring/webresources/personProfile/searchByNames";

var url_addCandidates = url_origin + "/hris_hiring/webresources/endorsements/save";
var url_addCandidatesUnique = url_origin + "/hris_hiring/webresources/endorsements/saveUnique";

var url_getEndorsement = url_origin + "/hris_hiring/webresources/endorsements/";
url_get_endorsementbyperson = url_origin + "/hris_hiring/webresources/endorsements/byperson/";

//jobss
var url_get_companies = url_origin + "/hris_hiring/webresources/com.nino.app.hrishiring.service.company";
var url_getJobsByCompany = url_origin + "/hris_hiring/webresources/jobs/bycompany/";
var url_getJob = url_origin + "/hris_hiring/webresources/com.nino.app.hrishiring.service.job/";
var url_addJob = url_origin + "/hris_hiring/webresources/jobs/save";
var url_updateJob = url_origin + "/hris_hiring/webresources/jobs/update/";

//Reports
var url_get_inittemp = url_origin + "/hris_hiring/webresources/reports/allPersonActivities";

var activityEntityID = null;

var working_jobqualification_id = "";
var jobQualification = null;

var personProfile = null;
var working_person_id = "";

var credentialID = "";
var credentialPersonID = "";
var credentialPersonName = "";
var credentialPersonflname = "";
var credential = null;

var contactInfo = null;
var working_contact_id = "";

var searchNamesForm_fname = "";
var searchNamesForm_lname = "";

var activities = null;
var working_activity_id = "";

$("#logout").submit(function (event) {
    event.preventDefault();
    $.ajax({
        type: 'GET',
        url: url_origin + '/hris_hiring/webresources/hrisaccount/logout',
        //"Access-Control-Allow-Origin: ": "*",
        success: function (data) {
            if (data == "success") {
                window.location.href = url_origin + "/hris_hiring/index.html?logoutok";
            }
        }
    });
});

function checkCredential(callback) {
    $.ajax({
        type: 'GET',
        url: get_credential_url,
        "Access-Control-Allow-Origin: ": "*",
        success: function (data) {
            //alert("done get" + data)
            if (data != null) {
                //alert("success");
                credential = data;
                credentialID = $(data).find("credential").find("accountID").text();

                credentialPersonID = $(data).find("credential").find("person").find("idPerson").text();
                credentialPersonName = $(data).find("credential").find("person").find("name").text();
                credentialPersonflname = $(data).find("credential").find("person").find("firstName").text() + " " +
                        $(data).find("credential").find("person").find("lastName").text();

                if (credentialID == "") {
                    window.location.href = url_origin + "/hris_hiring/index.html?nologin";
                } else {
                    if (callback && typeof (callback) === "function") {
                        callback();
                    }
                }
            } else {
                window.location.href = url_origin + "/hris_hiring/index.html?nologin";
            }
        },
        error: function () {
            setTimeout(showAlert("The application found problem on your credential, please contact administrator."), 3000);
            window.location.href = url_origin + "/hris_hiring/index.html?nologin";
        }
    });
}

function showAlert(msg) {
    $("#alert-msg").html("<p>" + msg + "</p>");
    $("#myModalalert").modal();
}

function lookupSelectValue(url, selinput, objname, opt_id_dom, name_dom, active_id, callback) {

    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {

            if (selinput != null) {

                $(data).find(objname).each(function () {
                    var opt_text = $(this).children(name_dom).text();
                    console.log("option text: " + opt_text);
                    var opt_id = $(this).find(opt_id_dom).text();
                    if (active_id == opt_id) {
                        $(selinput).append("<option selected='selected' value='" + opt_id + "'>" + opt_text + "</option>");
                    } else {
                        $(selinput).append("<option value='" + opt_id + "'>" + opt_text + "</option>");
                    }
                });

                if (callback && typeof (callback) === "function") {
                    //do something here from your call back function
                    //console.log("Calling the callback inside the function getActivities...")
                    callback(data);
                }

            }
        },
        error: function (jqXHR, status) {
            showAlert("Application Error encountered in getFramework: " + status);
        }
    });
}

function saveProfile(personformdata, callback) {
    console.log("saving new record person profile.");
    $.ajax({
        type: 'POST',
        url: create_personalprofile_url,
        contentType: 'application/json',
        data: personformdata,
        success: function (data) {
            if (callback && typeof (callback) === "function") {
                personProfile = data;
                working_person_id = $(data).find("idPerson").text();
                //do something here from your call back function
                //console.log("Calling the callback inside the function getActivities...")
                callback(data);
            }
            ;
        }
    });
}

function saveJobQualification(jobQualificationFormData, callback) {
    //console.log("saving new record person profile.");
    $.ajax({
        type: 'POST',
        url: save_jobqualification_url + working_person_id,
        contentType: 'application/json',
        data: jobQualificationFormData,
        success: function (data) {
            jobQualification = data;
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                //console.log("Calling the callback inside the function getActivities...")
                callback();
            }
        }
    });
}

function saveContact(contactFormData, callback) {
    console.log("saveContact called");
    $.ajax({
        type: 'POST',
        url: save_contact_url + working_person_id,
        contentType: 'application/json',
        data: contactFormData,
        success: function (data) {
            contactInfo = data;
            console.log("Save contact done");
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                //console.log("Calling the callback inside the function getActivities...")
                callback();
            }
        },
        error: function (jqXHR, status) {
            console.log(status);
        }
    });
}

function updateJobQualification(jobQualificationData, callback) {
    $.ajax({
        type: 'PUT',
        url: update_jobqualification_url + working_person_id,
        contentType: 'application/json',
        data: jobQualificationData,
        success: function (data) {
            jobQualification = data;
            if (callback && typeof (callback) === "function") {
                callback();
            }
        },
        error: function () {
            showAlert("Application Error!");
        }
    });
}

function updateContact(contactData, callback) {
    $.ajax({
        type: 'PUT',
        url: update_contact_url + working_person_id,
        contentType: 'application/json',
        data: contactData,
        success: function (data) {
            console.log("update successfull");
            contactInfo = data;
            if (callback && typeof (callback) === "function") {
                callback();
            }
        },
        error: function () {
            showAlert("Application Error!");
        }
    });
}

function updateProfile(profileData, callback) {
    $.ajax({
        type: 'PUT',
        url: update_personalprofile_url + working_person_id,
        contentType: 'application/json',
        data: profileData,
        success: function (data) {
            if (callback && typeof (callback) === "function") {
                callback(data);
            }
        },
        error: function () {
            showAlert("Application Error! Please contact system admin.");
        }
    });
}

function updateActivity(activityData, callback) {
    $.ajax({
        type: 'PUT',
        url: update_activities_url,
        contentType: 'application/json',
        data: activityData,
        success: function (data) {
            console.log("update successfull");
            if (callback && typeof (callback) === "function") {
                callback(data);
            }
        },
        error: function () {
            alert("Application Error!");
        }
    });
}

function addEndorsement(endorsementdata, callback) {
    $.ajax({
        type: 'PUT',
        url: url_addCandidates,
        contentType: 'application/json',
        data: endorsementdata,
        success: function (data) {
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                //console.log("Calling the callback inside the function getActivities...")
                callback(data);
            }
        },
        error: function () {
            showAlert("Application Error! Please contact system admin.");
        }
    });
}
function saveActivity(activityData, callback) {
    $.ajax({
        type: 'POST',
        url: save_activities_url,
        contentType: 'application/json',
        data: activityData,
        success: function (data) {
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                //console.log("Calling the callback inside the function getActivities...")
                callback(data);
            }
        }
    });
}

function getActivities(callback) {
    $.ajax({
        type: 'GET',
        url: get_activities_url + working_person_id,
        success: function (data) {
            if (data == "noresult") {
                //showAlert("Warning! No activity found for this candidate: " + working_person_id);
            } else {
                activities = data;
            }
            if (callback && typeof (callback) === "function") {
                callback(data);
            }
        },
        error: function (jqXHR, status) {
            showAlert("Application Error Found: " + status);
        }
    });
}


function searchByPersonNames(person, callback) {
    $.ajax({
        type: 'POST',
        url: url_searchByPersonNames,
        contentType: 'application/json',
        data: person,
        //dataType: 'json',
        success: function (data) {
            //console.log("Success on search by person");
            if (callback && typeof (callback) === "function") {
                callback(data);
            }
        },
        error: function (jqXHR, status) {
            showAlert("Application Error Found: " + status);
        }
    });
}

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

function getPersonalProfile(id, callback) {
    console.log("------>getPersonalProfile");
    $.ajax({
        type: 'GET',
        url: get_personProfile_url + id,
        success: function (data) {

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

function getPersonalProfileData(id, callback) {
    $.ajax({
        type: 'GET',
        url: get_personProfile_url + id,
        success: function (data) {
            if (callback && typeof (callback) === "function") {
                callback(data);
            }
        },
        error: function (jqXHR, status) {
            showAlert("Error message: " + status);
        }
    });
}

function getActivityEntityID(callback) {
    console.log("get Activity Entity URL ");
    $.ajax({
        type: 'GET',
        url: get_activityEntity_url + working_person_id,
        success: function (data) {
            activityEntityID = $(data).find("idpersonactivities").text();
            console.log("Entity ID " + activityEntityID);
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

function getActivityEntityID1(pid, callback) {
    $.ajax({
        type: 'GET',
        url: get_activityEntity_url + pid,
        success: function (data) {
            activityEntityID = $(data).find("idpersonactivities").text();
            if (callback && typeof (callback) === "function") {
                callback(activityEntityID);
            }
        },
        error: function (jqXHR, status) {
            showAlert("Application Error Found: " + status);
        }
    });
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

function getPersonByName(fullname, callback) {
    $.ajax({
        type: 'GET',
        url: url_searchByFullName + fullname,
        success: function (data) {
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                console.log("Calling the callback inside the function getActivities...");
                callback(data);
            }
            ;
        },
        error: function (jqXHR, status) {
            showAlert("Application Error Found: " + status);
        }
    });
}

function getGeneric(url, callback) {
    console.log("------>Generic");
    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {
            if (data == null) {
                showAlert("No record found for rec#: " + searchStr);
            } else {
                if (callback && typeof (callback) === "function") {
                    callback(data);
                }
            }
        },
        error: function (jqXHR, status) {
            showAlert("Error message: " + status);
        }
    });
    console.log("<------");
}

function initWithProfile() {
    getActivityEntityID();
    prepActivePerson(personProfile);
    $("#leftnavbar").removeClass("disableddiv");

    $("#section1").load("htmlcomponents/generalInformation.html", function () {
        $.getScript("js/component/generalInformation.js");
    });
    $("#remarks-panel").remove();


    $("#rem_container").load("htmlcomponents/activities.html", function () {
        $.getScript("js/component/activities.js");
        activatePill("activity_pill");
    });

}

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
}

function ifnull(type) {
    if (type === null) {
        return "";
    } else {
        return type;
    }

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

function showLoaderActivities() {
    $("#loader_section2").addClass("loader-sm");
    $("#loader_section2").addClass("overlay_center");
    $("#section2").addClass("disableddiv");
}

function hideLoaderActivities() {
    $("#loader_section2").removeClass("loader-sm");
    $("#loader_section2").removeClass("overlay_center");
    $("#section2").removeClass("disableddiv");
}

function TimeStampToDate(xmlDate)
{
    var dt = new Date(xmlDate);
    return (dt.getMonth() + 1) + "/" + dt.getDay() + "/" + dt.getFullYear();
}

function FormatTimestamp(xmlTimestamp)
{
    var dt = new Date(xmlTimestamp);
    return (dt.getMonth() + 1) + "/" + dt.getDay() + "/" + dt.getFullYear() + " " + dt.getHours() + ":" + dt.getMinutes();
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

function activatePill(pillid) {

    $("#remarks_pill").removeClass("active");
    $("#activity_pill").removeClass("active");

    $("#" + pillid).addClass("active");
}


// Changes XML to JSON
function xmlToJson(xml) {

    // Create the return object
    var obj = {};

    if (xml.nodeType == 1) { // element
        // do attributes
        if (xml.attributes.length > 0) {
            obj["@attributes"] = {};
            for (var j = 0; j < xml.attributes.length; j++) {
                var attribute = xml.attributes.item(j);
                obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
            }
        }
    } else if (xml.nodeType == 3) { // text
        obj = xml.nodeValue;
    }

    // do children
    if (xml.hasChildNodes()) {
        for (var i = 0; i < xml.childNodes.length; i++) {
            var item = xml.childNodes.item(i);
            var nodeName = item.nodeName;
            if (typeof (obj[nodeName]) == "undefined") {
                obj[nodeName] = xmlToJson(item);
            } else {
                if (typeof (obj[nodeName].push) == "undefined") {
                    var old = obj[nodeName];
                    obj[nodeName] = [];
                    obj[nodeName].push(old);
                }
                obj[nodeName].push(xmlToJson(item));
            }
        }
    }
    return obj;
}
;

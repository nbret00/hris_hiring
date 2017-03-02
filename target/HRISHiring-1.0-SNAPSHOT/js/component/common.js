
//register global variable for sharing data between components
var get_personProfile_url = "http://localhost:8080/hris_hiring/webresources/person/";
var update_personalprofile_url = "http://localhost:8080/hris_hiring/webresources/person/";
var create_personalprofile_url = "http://localhost:8080/hris_hiring/webresources/personProfile/save";

var get_contact_url = "http://localhost:8080/hris_hiring/webresources/contact/";
var update_contact_url = "http://localhost:8080/hris_hiring/webresources/contact/";
var save_contact_url = "http://localhost:8080/hris_hiring/webresources/contact/save/";

var get_jobQualification_url = "http://localhost:8080/hris_hiring/webresources/jobqualification/";
var save_jobqualification_url = "http://localhost:8080/hris_hiring/webresources/jobqualification/save/";
var update_jobqualification_url = "http://localhost:8080/hris_hiring/webresources/jobqualification/";

var get_activities_url = "http://localhost:8080/hris_hiring/webresources/activities/act/";
var get_activity_status_tp = "http://localhost:8080/hris_hiring/webresources/nsbactivitystatustp";
var get_activity_tp = "http://localhost:8080/hris_hiring/webresources/nsbactivitytp";
var save_activities_url = "http://localhost:8080/hris_hiring/webresources/activities/save";
var update_activities_url = "http://localhost:8080/hris_hiring/webresources/activities/";//put
var get_activityEntity_url = "http://localhost:8080/hris_hiring/webresources/activities/activityEntity/";

var get_remarks_url = "http://localhost:8080/hris_hiring/webresources/activities/remarksByPerson/";
var add_remarks_url = "http://localhost:8080/hris_hiring/webresources/activities/remarks/add";

var url_searchByNames = "http://localhost:8080/hris_hiring/webresources/jobqualification/searchFirstname/";

var activityEntityID = null;

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


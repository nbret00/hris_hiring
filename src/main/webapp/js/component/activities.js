/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    var activity_container_row = document.getElementById("activities-container-row").cloneNode(true);

    var activity_row = document.getElementById("activityRow").cloneNode(true);

    $(activity_container_row).find("#activityRow").remove();
    
    showLoaderActivities();
    init();


    function init() {
        
        document.getElementById("activityform").reset();
        $("#activities-container-row").remove();

        getActivities(function () {
            showActivityForm(function () {
                prepActivities();
                hideLoaderActivities();
            });
        });
    }
    
/* not used. this can be future solution implementing singleton
    var activitytp = null;
    function getActivityTypes() {
        if (activitytp = "" || activitytp == null) {
            getGeneric(get_activity_tp, function (data) {
                activitytp = data;
            })
        }
    }

    function layoutSelValue(data,objname,name_dom,opt_id_dom,active_id,selinput) {

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

*/

    function showActivityForm(callback) {
        console.log("working activity form...showActivityForm");

        var activity_container = activity_container_row.cloneNode(true);

        $(activities).find("nsbActivities").each(function () {
            //console.log("activity type: " + $(this).find("nsbActivityTp").find("name").text());
            var composeActivities = activity_row.cloneNode(true);
            //console.log("html: " + $(composeActivities).html());
            var activity_id = $(this).find("idSourcingActivities").text();
            $(composeActivities).attr("id", "act_row_" + activity_id);

            $(composeActivities).find("#activityUpdateForm").attr("id", "activityUpdateForm" + activity_id);
            $(composeActivities).find("#updateBut").click(function () {
                updateActivityHandler(activity_id)
            });

            $(composeActivities).find("#act_heading").text($(this).find("nsbActivityTp").find("name").text());
            $(composeActivities).find("#act_heading").attr("data-id", $(this).find("nsbActivityTp").find("idActivityTp").text());

            $(composeActivities).find("#act_id").text(activity_id);
            $(composeActivities).find("#act_status").attr("id", "act_status_" + activity_id);

            $(composeActivities).find("#act_updateddt").text(FormatTimestamp($(this).find("lastUpdatedDt").text()));
            $(composeActivities).find("#act_updatedby").text($(this).find("updatedByName").text());

            $(composeActivities).find("#act_description").text($(this).find("description").first().text());

            $(activity_container).find("#activities-container-col").append(composeActivities);
        });

        $("#activity_panel").append(activity_container);

        if (callback && typeof (callback) === "function") {
            //do something here from your call back function
            console.log("Calling the callback inside the function...showActivityForm");
            callback();
        }
    }

    function prepActivities() {
        
        $(activities).find("nsbActivities").each(function () {
            var sel_act_status_tp = $("#act_status_" + $(this).find("idSourcingActivities").text());
            //$(sel_act_status_tp).reset();
            lookupSelectValue(get_activity_status_tp, sel_act_status_tp, "nsbActivityStatusTp", "idactivityStatus", "name", $(this).find("nsbActivityStatusTp").find("idactivityStatus").text(), function () {
            })
        });
        
        saveActivityHandler();
        //populate dropdown for activity type
        var sel_act_status_tp = $("#act_status_new");
        $("#act_status_new").children().remove();
        lookupSelectValue(get_activity_status_tp, sel_act_status_tp, "nsbActivityStatusTp", "idactivityStatus", "name", "", function () {
        });
        var sel_act_tp = $("#act_type_new");
        $("#act_type_new").children().remove();
        lookupSelectValue(get_activity_tp, sel_act_tp, "nsbActivityTp", "idActivityTp", "name", "", function () {

        });
    }

    function saveActivityHandler() {
        showLoaderActivities();
        $("#activityform").submit(function (event) {
            event.preventDefault();
            console.log("saving new activity." + getActivityFormData());
            saveActivity(getActivityFormData(), function () {
                init();
            })
        });
    }

    function updateActivityHandler(param) {
        showLoaderActivities()
        var elem = "act_row_" + param;
        console.log("url for updating " + update_activities_url + param);
        updateActivity(getActivityFormDataUpdate(elem, param), function () {
            init();
        })
    }
    function getActivityFormDataUpdate(domData, activityID) {

        var working_dom = $("#" + domData);

        var activityData = JSON.stringify({
            idSourcingActivities: activityID,
            createdBy: credentialPersonID,
            updatedBy: credentialPersonID,
            updatedByName: credentialPersonName,
            //description: $(working_dom).find("#description").text(),
            nsbActivityStatusTp: {idactivityStatus: $("#" + domData).find("#act_status_" + activityID).val()},
            nsbActivityTp: {idActivityTp: $(working_dom).find("#act_heading").data("id")},
            nsbEntityActivities: {ididentityActivities: activityEntityID}
        });
        console.log("Activity data for update: " + activityData);
        return activityData;
    }

    function getActivityFormData() {
        var activityData = JSON.stringify({
            //idSourcingActivities: working_activity_id,
            createdBy: credentialPersonID,
            updatedBy: credentialPersonID,
            updatedByName: credentialPersonName,
            description: $('#description').val(),
            nsbActivityStatusTp: {idactivityStatus: $("#act_status_new").val()},
            nsbActivityTp: {idActivityTp: $("#act_type_new").val()},
            nsbEntityActivities: {idpersonactivities: activityEntityID}
        });
        return activityData;
    }

    function lookupSelectValueActivity(url, selinput, objname, opt_id_dom, name_dom, active_id, callback) {

        $.ajax({
            type: 'GET',
            url: url,
            success: function (data) {

                if (selinput != null) {

                    $(data).find(objname).each(function () {
                        var opt_text = $(this).get(name_dom).text();
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


})
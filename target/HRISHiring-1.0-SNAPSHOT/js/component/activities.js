/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    var activity_container_row = document.getElementById("activities-container-row").cloneNode(true);
    
    var activity_row = document.getElementById("activityRow").cloneNode(true);
    
    $(activity_container_row).find("#activityRow").remove();
    
    init();

    function init() {
        document.getElementById("activityform").reset();
        $("#activities-container-row").remove();
        
        getActivities(function () {
            showActivityForm(function () {
                prepActivities();
            });
        });
    }
    
    function showActivityForm(callback) {
        console.log("working activity form...showActivityForm");
        
        var activity_container = activity_container_row.cloneNode(true);

        $(activities).find("nsbActivities").each(function () {
            console.log("activity type: " + $(this).find("nsbActivityTp").find("name").text());
            var composeActivities = activity_row.cloneNode(true);
            //console.log("html: " + $(composeActivities).html());
            var activity_id = $(this).find("idSourcingActivities").text();
            $(composeActivities).attr("data-act-id", activity_id);
            $(composeActivities).find("#act_heading").text($(this).find("nsbActivityTp").find("name").text());
            $(composeActivities).find("#act_id").text(activity_id);
            $(composeActivities).find("#act_status").attr("id", "act_status_" + activity_id);
            $(composeActivities).find("#act_description").text($(this).find("description").text());
            //.text("Activity Type: "+$(this).find("nsbActivityTp").find("name").text());                         
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
            lookupSelectValue(get_activity_status_tp, sel_act_status_tp, "nsbActivityStatusTp", "idactivityStatus", "name", $(this).find("nsbActivityStatusTp").find("idactivityStatus").text(), function () {
                console.log("Called getFramework act status");
            })
        })
        updateActivityHandler();
        saveActivityHandler();
        //populate dropdown for activity type
        var sel_act_status_tp = $("#act_status_new");
        lookupSelectValue(get_activity_status_tp, sel_act_status_tp, "nsbActivityStatusTp", "idactivityStatus", "name", "1", function () {
        });
        var sel_act_tp = $("#act_type_new");
        lookupSelectValue(get_activity_tp, sel_act_tp, "nsbActivityTp", "idActivityTp", "name", "2", function () {

        });
    }

    function saveActivityHandler() {
        $("#activityform").submit(function (event) {
            event.preventDefault();
            //console.log("clicked contactSaveUpdateHandler :" + $("#sourcingBut").text());
            //activityData = getActivityFormData();
            console.log("saving new activity." + getActivityFormData());
            $.ajax({
                type: 'POST',
                url: save_activities_url,
                contentType: 'application/json',
                data: getActivityFormData(),
                success: function (data) {
                    init();
                }
            });
        });
    }
    function updateActivityHandler() {
        $("#activityform").submit(function (event) {
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
    function getActivityFormData() {
        var activityData = JSON.stringify({
            idSourcingActivities: working_activity_id,
            createdBy: credentialID,
            updatedBy: credentialID,
            description: $('#description').val(),
            nsbActivityStatusTp: {idactivityStatus: $("#act_status_new").val()},
            nsbActivityTp: {idActivityTp: $("#act_type_new").val()},
            nsbEntityActivities: {ididentityActivities: working_person_id}
        });
        return activityData;
    }
    /*
     function getActivityNewPersonData() {
     var activityData = JSON.stringify({
     //idSourcingActivities: working_activity_id,
     createdBy: credentialID,
     updatedBy: credentialID,
     description: 'This activity pertains to the initial creation of the record.',
     nsbActivityStatusTp: {idactivityStatus: '1'},
     nsbActivityTp: {idActivityTp: '1'},
     nsbEntityActivities: {ididentityActivities: working_person_id}
     });
     return activityData;
     }
     */

})
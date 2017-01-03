/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    getActivities(function () {
        showActivityForm(function () {
            //show remarks()    
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
            })
            var sel_act_tp = $("#act_type");
            lookupSelectValue(get_activity_tp, sel_act_tp, "nsbActivityTp", "idActivityTp", "name", "2", function () {

            })
        });
    });

    function showActivityForm(callback) {
        //console.log(working_sourcing_id + "() called....." + $(sourcing).html());
        console.log("working activity form");
        var tempActivityDisplay = document.getElementById("activityRow").cloneNode(true);
        //console.log("html: "+$(tempActivityDisplay).html());
        $("#activityRow").remove();
        $(activities).find("nsbActivities").each(function () {
            console.log("activity type: " + $(this).find("nsbActivityTp").find("name").text());
            var composeActivities = tempActivityDisplay.cloneNode(true);
            //console.log("html: " + $(composeActivities).html());
            var activity_id = $(this).find("idSourcingActivities").text();
            $(composeActivities).attr("data-act-id", activity_id);
            $(composeActivities).find("#act_heading").text("Activity Type: " + $(this).find("nsbActivityTp").find("name").text());
            $(composeActivities).find("#act_id").text("Activity ID: " + activity_id);
            $(composeActivities).find("#act_status").attr("id", "act_status_" + activity_id);
            $(composeActivities).find("#act_description").text($(this).find("description").text());
            //.text("Activity Type: "+$(this).find("nsbActivityTp").find("name").text());                         
            $("#activity_panel").append(composeActivities);
        });
        if (callback && typeof (callback) === "function") {
            //do something here from your call back function
            console.log("Calling the callback inside the function...showActivityForm");
            callback();
        }
        ;

    }
    ;
})
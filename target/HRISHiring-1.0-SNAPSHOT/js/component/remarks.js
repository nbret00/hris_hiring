/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    console.log("remarks");
    var remarks_dom = $("#row_model").clone(true);

    var default_activity_id = null; // this will be used to associate when adding remark

    if (working_person_id == null) {
        console.log("activities is null");
        $("#remarks").remove();
    } else {
        console.log("init remarks...");
        if (activityEntityID == "" || activityEntityID == null) {
            console.log("No Person Activity... setting...");
            getActivityEntityID(function(){
                console.log("Person Activity: "+activityEntity);
                init();
            });
        }else{
            console.log("Person Activity ID: "+activityEntityID);
            init();
        }
    }

    function init() {
        $("#remarks_field").val("");
        $("#remarks").remove();
        
        getActivityRemarks(working_person_id, function (data) {
            //var dom_select_act = "div[data-act-id=\'" + activityID + "\']";
            var remarks_working_dom = $(remarks_dom).clone(true);
            var remarks_working_row = $(remarks_working_dom).find("#remarks_row").clone(true);

            $(remarks_working_dom).find("#remarks_row").remove();

            $(data).find("nsbRemarks").each(function () {
                var i_remarks_dom = $(remarks_working_row).clone(true);
                i_remarks_dom.find("#remarks_body").text($(this).find("remarks").text());
                i_remarks_dom.find("#dt_created").text(FormatTimestamp($(this).find("createdDt").text()));
                i_remarks_dom.find("#created_by").text($(this).find("createdByName").text()+" ("+$(this).find("createdBy").text()+")");
                $(remarks_working_dom).find("#remarks").append(i_remarks_dom);
            });
            $("#row_model").append(remarks_working_dom);
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                console.log("Calling the callback inside the function getActivities...")
                callback();
            }
        });
    }


    $("#addRemarksForm").submit(function (event) {
        event.preventDefault();
        var form_data = JSON.stringify({
            createdBy: $(personProfile).find("name").text(),
            createdByName: credentialPersonflname,
            remarks: $("#remarks_field").val(),
            identityActivities: {idpersonactivities: activityEntityID}
        });
        console.log("New Remarks: "+form_data);
        $.ajax({
            type: 'PUT',
            url: add_remarks_url,
            contentType: 'application/json',
            data: form_data,
            success: function (data) {
                init();
            }
        });

    });

})

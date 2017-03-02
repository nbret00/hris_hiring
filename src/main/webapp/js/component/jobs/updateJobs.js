


$(document).ready(function () {

    var url_getJob = "http://localhost:8080/hris_hiring/webresources/com.nino.app.hrishiring.service.job/";
    var url_addJob = "http://localhost:8080/hris_hiring/webresources/jobs/save";
    var url_updateJob = "http://localhost:8080/hris_hiring/webresources/jobs/update/";

    var update_jobs_data = null;

    console.log("ID: " + updateJobsID);
    $("#endorsement-but").click(function () {
        $("#rem_container").load("htmlcomponents/jobs/endorsement.html", function () {
            $.getScript("js/component/jobs/endorsement.js");
        });
    })
    $("#addcand-but").click(function () {
        $("#rem_container").load("htmlcomponents/jobs/addCandidate.html", function () {
            $.getScript("js/component/jobs/addCandidate.js");
        });
    })
    $("#jobsum-but").click(function () {
        $("#rem_container").load("htmlcomponents/jobs/updateJobs.html", function () {
            $.getScript("js/component/jobs/updateJobs.js");
        });
    })

    init();

    function init() {
        if (updateJobsID == "") {
            //do nothing this is a create new record
            $("#jobBut").text("Create New Job Opening");
        } else {
            //retrive
            $.ajax({
                type: 'GET',
                url: url_getJob + updateJobsID,
                success: function (data) {
                    update_jobs_data = data;
                    setFormData(data);
                }
            })
        }
    }

    function setFormData(result) {
        $("#jobtitle").val($(result).find("title").text());
        $("#description_short").text($(result).find("description").text());
        $("#description_long").text($(result).find("descriptionLong").text());
        $("#qualification").val($(result).find("qualifications").text());
        $("#targetposition").val($(result).find("responsibility").text());
        $("#dateRecieved").val($(result).find("dateRecieved").text());
        $("#dateClosing").val($(result).find("closingDate").text());//todo
        $("#openPosition").val($(result).find("openPosition").text());
        $("#status").val($(result).find("status").text());
        $("#targetEndorse").val($(result).find("ptargetToEndorse").text());
        $("#assignedTo").val($(result).find("PAssignement").text());
        $("#targetMatch").val($(result).find("ptargetToMatch").text());
    }

    function getFormData() {
        var fd = JSON.stringify({
            title: $("#jobtitle").val(),
            description: $("#description_short").text(),
            descriptionLong: $("#description_long").text(),
            qualifications: $("#qualification").val(),
            responsibility: $("#targetposition").val(),
            dateRecieved: $("#dateRecieved").val(),
            closingDate: $("#dateClosing").val(),
            openPosition: $("#openPosition").val(),
            status: $("#status").val(),
            ptargetToEndorse: $("#targetEndorse").val(),
            PAssignement: $("#assignedTo").val(),
            ptargetToMatch: $("#targetMatch").val(),
            companyIdclient: {idclient: updateCompanyID}
        });
        return fd;
    }

    if ($("#jobBut").text() == "Create New Job Opening") {
        $("#jobForm").submit(function (event) {
            event.preventDefault();
            console.log(getFormData());
            $.ajax({
                type: 'POST',
                url: url_addJob,
                contentType: 'application/json',
                data: getFormData(),
                success: function (data) {
                    update_jobs_data = data;
                    setFormData(data);
                    $("#jsGrid").jsGrid("loadData").done(function () {

                    });
                }
            })
        })
    }
    if ($("#jobBut").text() == "Update") {
        $("#jobForm").submit(function (event) {
            event.preventDefault();
            console.log(getFormData());
            $.ajax({
                type: 'POST',
                url: url_updateJob + updateJobsID,
                contentType: 'application/json',
                data: getFormData(),
                success: function (data) {
                    update_jobs_data = data;
                    setFormData(data);
                    $("#jsGrid").jsGrid("loadData").done(function () {

                    });
                }
            })
        })
    }


})



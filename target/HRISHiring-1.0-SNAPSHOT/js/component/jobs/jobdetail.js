
$(document).ready(function () {

    $.getScript("js/component/jobs/jobsnavbar.js");

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
                    //update_jobs_data = data;
                    setFormData(data);
                }
            })
        }
    }

    function setFormData(result) {
        $("#jobid").text($(result).find("title").text());
        $("#jobtitle").text($(result).find("title").text());
        $("#jobdescription").text($(result).find("description").text());
        $("#jobtargetpos").text($(result).find("responsibility").text());
        $("#jobstatus").text($(result).find("status").text());
        $("#jobcompany").text($(result).find("companyName").text());
        $("#jobqualification").text($(result).find("qualifications").text());
        $("#jobdateopen").text(FormatTimestamp($(result).find("dateRecieved").text()));
        $("#jobclosingdate").text($(result).find("closingDate").text());//todo
        /*
        $("#openPosition").val($(result).find("openPosition").text());
        $("#targetEndorse").val($(result).find("ptargetToEndorse").text());
        $("#assignedTo").val($(result).find("PAssignement").text());
        $("#targetMatch").val($(result).find("ptargetToMatch").text());
        */
    }

    $("#jsGridEndorcement").jsGrid({
        width: "100%",
        height: "315",
        sorting: true,
        paging: true,
        autoload: true,
        controller: {
            loadData: function (filter) {

                console.log("filter: " + updateCompanyID);

                return $.ajax({
                    type: "GET",
                    url: url_getEndorsement + updateCompanyID + "/" + updateJobsID,
                    dataType: "JSON"
                })
            }
        },
        rowClick: function (args) {
            var srow = this.rowByItem(args.item);
            console.log("Test click " + args.item.idjobpk);
            updateJobsID = args.item.idjobpk;
        },
        fields: [
            {title: 'Rec #', name: 'personidPerson.idPerson', width: 20, key: true},
            {title: 'First Name', name: 'personidPerson.firstName', type: 'text', width: 30},
            {title: 'Lastname', name: 'personidPerson.lastName', type: 'text', width: 30},
            {title: 'Status', name: 'status', type: 'text', width: 20},
            {title: 'Date', name: 'endorsedDate', type: 'text', width: 30}
        ]
    });
})



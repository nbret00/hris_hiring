
$(document).ready(function () {
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

    $("#jsGridEndorcement").jsGrid({
        width: "100%",
        height: "600",
        sorting: true,
        paging: true,
        autoload: true,
        controller: {
            loadData: function (filter) {

                console.log("filter: " + updateCompanyID);

                return $.ajax({
                    type: "GET",
                    url: url_getEndorsement+updateCompanyID+"/"+updateJobsID,
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



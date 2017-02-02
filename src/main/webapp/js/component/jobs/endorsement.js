
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

                console.log("filter: " + JSON.stringify(filter));

                return $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/hris_hiring/webresources/endorsements/"+updateCompanyID,
                    data: JSON.stringify(filter),
                    contentType: "application/json",
                    dataType: "JSON"
                })
            }
        },
        rowClick: function (args) {
            var srow = this.rowByItem(args.item);
            $(srow).addClass("selected-row");

            console.log("Test click " + args.item.idjobpk);
            updateJobsID = args.item.idjobpk;
            $("#rem_container").load("htmlcomponents/jobs/updateJobs.html", function () {
                $.getScript("js/component/jobs/updateJobs.js");
            });

        },
        fields: [
            {title: 'Job Num', name: 'companyIdclient.companyName', width: 50, key: true},
            {title: 'Job Title', name: 'personidPerson.firstName', type: 'text', width: 50},
            {title: 'Description', name: 'personidPerson.lastName', type: 'text', width: 200}
        ]
    });
})



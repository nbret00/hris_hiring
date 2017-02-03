
var updateJobsID = "";
var updateCompanyID = "";
var updateCompanyName = "";

var url_get_companies = "http://localhost:8080/hris_hiring/webresources/com.nino.app.hrishiring.service.company";
var url_getJobsByCompany = "http://localhost:8080/hris_hiring/webresources/jobs/bycompany/";

$(document).ready(function () {

    init();

    function init() {
        lookupSelectValue(url_get_companies, sel_company, "company", "idclient", "companyName", "", function () {
            console.log("Called getFramework act status");

            $("#sel_company").click(function () {
                console.log("value selected : " + $(this).val());
                updateCompanyID = $(this).val();
                updateCompanyName = $(this).text();
                $("#parag_company").text("Create Job for the selected company");
                $("#jsGrid").jsGrid("loadData").done(function () {
                    
                });

            })
            $("#parag_company").click(function () {

                $("#rem_container").load("htmlcomponents/jobs/updateJobs.html", function () {
                    updateJobsID = "";
                    $.getScript("js/component/jobs/updateJobs.js");
                    $("#jobBut").text("Create New");
                    $("#endorsement-but").text("");
                    $("#addcand-but").text("");
                });

            })
        })
    }

    $("#jsGrid").jsGrid({
        width: 570,
        height: 450,
        sorting: true,
        paging: true,
        autoload: false,
        controller: {
            loadData: function (filter) {

                console.log("filter: " + JSON.stringify(filter) + "--" + url_getJobsByCompany);

                return $.ajax({
                    type: "GET",
                    url: url_getJobsByCompany + updateCompanyID,
                    contentType: "application/json",
                    dataType: "JSON"
                })
            }
        },
        rowClick: function (args) {
            //var srow = this.rowByItem(args.item);
            //$(srow).addClass("selected-row");
            updateJobsID = "";
            updateCompanyID = "";
            updateCompanyName = "";

            console.log("Test click " + args.item.idjobpk);
            updateJobsID = args.item.idjobpk;
            $("#rem_container").load("htmlcomponents/jobs/updateJobs.html", function () {
                $.getScript("js/component/jobs/updateJobs.js");
            });

        },
        fields: [
            {title: 'Job Num', name: 'idjobpk', width: 50, key: true},
            {title: 'Job Title', name: 'title', type: 'text', width: 50},
            {title: 'Description', name: 'description', type: 'text', width: 200},
            {title: 'Status', name: 'status', type: 'text', width: 50}
        ]
    });
})



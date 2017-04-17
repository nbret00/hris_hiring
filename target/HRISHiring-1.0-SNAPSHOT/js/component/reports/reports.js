
var updateJobsID = "";
var updateCompanyID = "";
var updateCompanyName = "";

$(document).ready(function () {
/*
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
                    $("#endorsement-but").text("");
                    $("#addcand-but").text("");
                });

            })
        })
    }
*/

    $("#jsGridReports").jsGrid({
        width: "100%",
        height: 500,
        sorting: true,
        paging: true,
        pageSize: 20,
        filtering: true,
        pageButtonCount: 3,
        autoload: true,
        controller: {
            loadData: function (filter) {

                console.log("filter: " + JSON.stringify(filter) + "--" + filter.activity);
                var act = "nah";
                if (filter.activity != ""){
                    act = filter.activity;
                }
                var acttp = "nah";
                if (filter.activtyTp != ""){
                    acttp = filter.activtyTp;
                }
                var ec = "nah";
                if (filter.endorsedCompany != ""){
                    ec = filter.endorsedCompany;
                }
                var ej = "nah";
                if (filter.endorsedJob != ""){
                    ej = filter.endorsedJob;
                }
                
                console.log("Search url: "+ url_get_inittemp+"/"+act+"/"+acttp+"/"+ec+"/"+ej);
                return $.ajax({
                    type: "GET",
                    url: url_get_inittemp+"/"+act+"/"+acttp+"/"+ec+"/"+ej,
                    contentType: "application/json",
                    dataType: "JSON"
                })
            }
        },
        rowClick: function (args) {
            //var srow = this.rowByItem(args.item);
            //$(srow).addClass("selected-row");

            console.log("Test click " + args.item.pid);

        },
        fields: [
            {title: 'Person #', name: 'pid', width: 30, key: true},
            {title: 'Name', name: 'name', type: 'text', filtering: false, width: 50},
            {title: 'First name', name: 'fname', type: 'text', filtering: false, width: 50},
            {title: 'Last name', name: 'lname', type: 'text', filtering: false, width: 50},
            {title: 'Job Title', name: 'jobTitle', type: 'text', filtering: false, width: 50},
            {title: 'Company', name: 'company', type: 'text', filtering: false, width: 50},
            {title: 'Activity', name: 'activity', type: 'select', items:[{ Name: "", Id: 0 },{ Name: "Sourced", Id: 1 },{ Name: "Contacting", Id: 2 },{ Name: "Endorsing", Id: 3 }],valueField: "Id",textField: "Name", width: 50},
            {title: 'Activity Type', name: 'activtyTp', width: 50, type: 'select', items:[
                    {Name: "", id:0},
                    {Name: "Invited", id:1},
                    {Name: "Accepted", id:2},
                    {Name: "Contacted", id:3},
                    {Name: "Not Interested", id:4},
                    {Name: "Not Qualified", id:5},
                    {Name: "Invalid", id:6},
                    {Name: "Engaged", id:7},
                    {Name: "For Endorse", id:8},
                    {Name: "Endorsed", id:9},
            ], valueField: "id",textField: "Name"},
            {title: 'Matched Company', name: 'endorsedCompany', type: 'text', width: 50},
            {title: 'Matched Job', name: 'endorsedJob', type: 'text', width: 50}
        ]
    });
})



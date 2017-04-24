
var updateJobsID = "";
var updateCompanyID = "";
var updateCompanyName = "";

$(document).ready(function () {

    checkCredential(function () {
        //console.log("credential = " + credentialID);
        init();
    });



    function init() {
        getGeneric(url_get_companies, function (data) {

            $(data).find("company").each(function () {
                var opt_id = $(this).find("idclient").text();
                var opt_text = "(" + opt_id + ") " + $(this).children("companyName").text();
                console.log("option text: " + opt_text);

                $("#sel_company").append("<option value='" + opt_id + "'>" + opt_text + "</option>");

            });
            $("#sel_company").click(function () {
                console.log("value selected : " + $(this).val());
                updateCompanyID = $(this).val();
                updateCompanyName = $(this).text();
                //$("#parag_company").text("Create Job for the selected company");
                $("#parag_company").removeClass("disabled");
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
        });
        /*
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
         */
    }

    $("#jsGrid").jsGrid({
        width: "100%",
        height: "470",
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
            //TODO: clicked means selected and should highlight
            var srow = this.rowByItem(args.item);
            //console.log("test111");
            
            //$(this).addClass("selected-row");
            
            //$(srow).removeClass();
            $("#jsgrid,tr").removeClass();
            $(srow).addClass("selected-row");

            //console.log("Test click " + args.item.idjobpk);
            updateJobsID = args.item.idjobpk;
            $("#rem_container").load("htmlcomponents/jobs/jobdetail.html", function () {
                $.getScript("js/component/jobs/jobdetail.js");
            });

        },
        fields: [
            {title: 'Job #', name: 'idjobpk', width: 30, key: true},
            {title: 'Job Title', name: 'title', type: 'text', width: 50},
            {title: 'Status', name: 'status', type: 'text', width: 50},
            {title: 'Closing Date', name: 'closingDate', type: 'text', width: 50},
            {title: 'Assigned To', name: 'PAssignement', type: 'text', width: 50},
            {title: 'To Match (Num)', name: 'ptargetToMatch', type: 'text', width: 30},
            {title: 'To Endorse (Num)', name: 'ptargetToEndorse', type: 'text', width: 30}
        ]
    });
})




var updateJobsID = "";
var updateCompanyID = "";
var updateCompanyName = "";

$(document).ready(function () {

    checkCredential(function () {
        init();
    });

    var recset = $("#recmodel").clone(true);

    function loadJobMatching() {
        $("#recmodel").remove();
        var lookup_url_get_endorsementbyperson = url_get_endorsementbyperson + working_person_id;
        console.log("url: " + lookup_url_get_endorsementbyperson);
        getGeneric(lookup_url_get_endorsementbyperson, function (data) {
            $(data).find("endorsement").each(function () {
                var perrecset = $(recset).clone();
                console.log("data:" + $(this).find("jobIdjobpk").find("title").text());
                $(perrecset).find("#jobid").text($(this).find("jobIdjobpk").find("idjobpk").text());
                $(perrecset).find("#jcomp").text($(this).find("jobIdjobpk").find("companyIdclient").find("companyName").text());
                $(perrecset).find("#jtitle").text($(this).find("jobIdjobpk").find("title").text());
                $(perrecset).find("#jenddate").text(FormatTimestamp($(this).find("endorsedDate").text()));
                $("#tbody").append(perrecset);
            });
        });
    }

    function init() {
        loadJobMatching();

        $("#addmatchbtn").click(function () {
            $("#addMatchDiv").removeClass("disableddiv");
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
                    $("#jsGrid").jsGrid("loadData").done(function () {
                    });
                })
                $("#butdiv").addClass("disableddiv");
            });
        });
    }

    $("#jsGrid").jsGrid({
        width: 425,
        height: 300,
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
            console.log("Test click " + args.item.idjobpk);
            updateJobsID = args.item.idjobpk;
            var r = confirm("Click OK to continue adding job match - " + args.item.title);
            if (r) {
                var endorsement = JSON.stringify({
                    companyIdclient: {idclient: updateCompanyID},
                    personidPerson: {idPerson: working_person_id},
                    jobIdjobpk: {idjobpk: updateJobsID}
                })
                addEndorsement(endorsement, function () {
                    showAlert("Job match successfully added.");
                    $("#panel").remove();
                    $("#section1").load("htmlcomponents/candjobmatch/candjobsmatch.html", function () {
                        $.getScript("js/component/candjobmatch/candjobsmatch.js");
                    });
                })
            }


        },
        fields: [
            {title: 'Job #', name: 'idjobpk', width: 30, key: true},
            {title: 'Job Title', name: 'title', type: 'text', width: 50},
            {title: 'Status', name: 'status', type: 'text', width: 50},
            {title: 'Closing Date', name: 'closingDate', type: 'text', width: 50},
        ]
    });

})



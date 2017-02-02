


$(document).ready(function () {
    
    var url_getJob = "http://localhost:8080/hris_hiring/webresources/com.nino.app.hrishiring.service.job/";
    
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
        } else {
            //retrive
            $.ajax({
                type: 'GET',
                url: url_getJob + updateJobsID,
                success : function(data){
                    $("#jobtitle").val($(data).find("title").text());
                    $("#description_short").text($(data).find("description").text());
                    $("#description_long").text($(data).find("descriptionLong").text());
                    $("#qualification").val($(data).find("qualifications").text());
                    $("#targetposition").val($(data).find("responsibility").text());
                    $("#dateRecieved").val($(data).find("dateRecieved").text());
                    $("#dateClosing").text();//todo
                }
            })
        }

    }

})



/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$("#job-match-but, #cancelBut").click(function () {
    $("#rem_container").load("htmlcomponents/jobs/jobdetail.html", function () {
        $.getScript("js/component/jobs/jobdetail.js");
    });
}),
        $("#addcand-but").click(function () {
    $("#job_container").load("htmlcomponents/jobs/addCandidate.html", function () {
        $.getScript("js/component/jobs/addCandidate.js");
    });
}),
        $("#job-update-but").click(function () {
    $("#rem_container").load("htmlcomponents/jobs/updateJobs.html", function () {
        $.getScript("js/component/jobs/updateJobs.js");
    });
})

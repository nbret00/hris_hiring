/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

console.log("Testssss");

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

var url_searchByNames = "http://localhost:8080/hris_hiring/webresources/jobqualification/searchFirstname/";
var url_addCandidates = "http://localhost:8080/hris_hiring/webresources/endorsements/save";

//            $("#searchNameForm").ready(function (data) {

$(document).ready(function () {


    function getSearchFormData() {
        var person = JSON.stringify({
            firstName: $("#FirstName").val(),
            lastName: $("#LastName").val()
        });
        return person;
    }

    $("#searchResultSelCan").click(function () {
        console.log("value selected : " + $(this).val() + "----" + updateJobsID);
        if ($(this).val() == "") {
        } else {
            var r = confirm("This will add a match to the job. - " + $("#searchResultSelCan option:selected").text());
            if (r) {
                var endorsement = JSON.stringify({
                    companyIdclient: {idclient: updateCompanyID},
                    personidPerson: {idPerson: $(this).val()},
                    jobIdjobpk: {idjobpk: updateJobsID}
                })
                $.ajax({
                    type: 'PUT',
                    url: url_addCandidates,
                    contentType: 'application/json',
                    data: endorsement,
                    success: function (data) {
                        showAlert("Candidate has been added to this job opening.");
                        $(document).find("#searchResultSelCan").empty();
                    },
                    error: function () {
                        showAlert("Application Error! Please contact system admin.");
                    }
                });


                console.log("end: " + endorsement);
            } else {
                console.log("this is false");
            }
        }
    })



    $("#searchNamesForm").submit(function (event) {
        event.preventDefault();
        var search_url = "";
        var form_fname = $("#FirstName").val();
        //console.log("form_fname: " + form_fname);
        var form_lname = $("#LastName").val();
        //console.log("form_lname: " + form_lname);

        if (form_fname != "" && form_lname == "") {
            //console.log("search for f name");
            search_url = url_searchByNames + $("#FirstName").val();
        }
        if (form_lname != "" && form_fname == "") {
            search_url = "" + $("#LastName").val();
            //console.log("search for l name");
        }
        if (form_fname != "" && form_lname != "") {
            //console.log("search for f and l name");
        }
        $("#FirstName").val("");
        $("#LastName").val("");

        $(document).find("#searchResultSelCan").empty();

        var foreachresRecCol = $(document).find("#searchResultSelCan");
        $.ajax({
            type: 'GET',
            url: search_url,
            success: function (data) {
                console.log("data cound!!!" + $(data).find("searchResult").size());// + data.find("firstname").text());
                if ($(data).find("searchResult").size() > 0) {
                    $(data).find("searchResult").each(function () {

                        //var option = document.createElement("option");
                        //option.text = $(this).find("firstname").text();
                        foreachresRecCol.append("<option value='" + $(this).find("personID").text() + "'>" + $(this).find("firstname").text() + "</option>");//add(option);

                    });

                } else {
                    foreachresRecCol.append("<option>No result found.</option>");
                }
            }
        });
    })

})
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



console.log("loaded generalInformation.js");

//            $("#searchNameForm").ready(function (data) {

var searchResultDiv = $("#searchResultDiv").clone(true);
$("#searchResultDiv").remove();


$(document).ready(function () {

    function getSearchFormData() {
        var person = JSON.stringify({
            firstName: $("#FirstName").val(),
            lastName: $("#LastName").val(),
            name: $("#FullName").val()
        });
        return person;
    }


    $("#searchNamesFormGen").submit(function (event) {
        event.preventDefault();
        $("#searchResultDiv").remove();

        searchByPersonNames(getSearchFormData(), function (data) {
            console.log("1");
            
            if ($(data).find("searchResult").size() > 0) {

                var resclone = searchResultDiv.clone(true);

                var resRecCol = $(resclone).find("#searchNamesResultCol").clone(true);
                $(resclone).find("#searchNamesResultCol").remove();

                $(data).find("searchResult").each(function () {
                    console.log("is");
                    var foreachresRecCol = resRecCol.clone(true);
                    var recid = $(this).find("personID").text();
                    var recol = foreachresRecCol.find("#recnum");
                    $(recol).text(recid);
                    $(recol).attr("href", "home.html?recid=" + recid);
                    foreachresRecCol.find("#name").text($(this).find("name").text());
                    foreachresRecCol.find("#fnameCol").text($(this).find("firstname").text());
                    foreachresRecCol.find("#lnameCol").text($(this).find("lastname").text());
                    foreachresRecCol.find("#titlecol").text($(this).find("title").text());
                    foreachresRecCol.find("#companyCol").text($(this).find("company").text());
                    $(resclone).find("#tbody").append(foreachresRecCol);
                });

                $("#resultDivContainer").append(resclone);
                $("#myModal").modal();
                document.getElementById("searchNamesFormGen").reset();
            } else {

                var r = confirm("Click OK to create new record for " + $("#FirstName").val() + " " + $("#LastName").val());

                if (r == true) {

                    $("#panel").remove();

                    searchNamesForm_fname = $("#FirstName").val();
                    searchNamesForm_lname = $("#LastName").val();

                    $("#section1").load("htmlcomponents/createNewGeneralInformation.html", function () {
                        //showAlert("No duplicate record found for name: " + form_fname + " " + form_lname + ". Continue to create new candidate.");
                        $.getScript("js/component/createNewGeneralInformation.js");
                    });
                }
            }

        });

    })

})

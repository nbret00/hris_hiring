/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


console.log("loaded generalInformation.js");
var url_searchByNames = "http://localhost:8080/hris_hiring/webresources/jobqualification/searchFirstname/";
//            $("#searchNameForm").ready(function (data) {

var searchResultDiv = $("#searchResultDiv").clone(true);
$("#searchResultDiv").remove();

$(document).ready(function () {

    function getSearchFormData() {
        var person = JSON.stringify({
            firstName: $("#FirstName").val(),
            lastName: $("#LastName").val()
        });
        return person;
    }

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



        $("#searchResultDiv").remove();
        $.ajax({
            type: 'GET',
            url: search_url,
            success: function (data) {
                console.log("data cound!!!" + $(data).find("searchResult").size());// + data.find("firstname").text());
                if ($(data).find("searchResult").size() > 0) {

                    var resclone = searchResultDiv.clone(true);

                    var resRecCol = $(resclone).find("#searchNamesResultCol").clone(true);
                    $(resclone).find("#searchNamesResultCol").remove();

                    console.log($(resclone).html());

                    $(data).find("searchResult").each(function () {
                        var foreachresRecCol = resRecCol.clone(true);
                        var recid = $(this).find("personID").text();
                        var recol = foreachresRecCol.find("#recnum");
                        $(recol).text(recid);
                        $(recol).attr("href","home.html?recid="+recid);
                        foreachresRecCol.find("#fnameCol").text($(this).find("firstname").text());
                        foreachresRecCol.find("#lnameCol").text($(this).find("lastname").text());
                        foreachresRecCol.find("#titlecol").text($(this).find("title").text());
                        $(resclone).append(foreachresRecCol);
                    });

                    $("#resultDivContainer").append(resclone);
                } else {
                    $("#panel").remove();
                    searchNamesForm_fname = $("#FirstName").val();

                    $("#section1").load("htmlcomponents/createNewGeneralInformation.html", function () {
                        showAlert("No duplicate record found for name: " + form_fname + " " + form_lname + ". Continue to create new candidate.");

                        $.getScript("js/component/createNewGeneralInformation.js");
                    });
                }
            }
        });
    })

})



//            });
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
            lastName: $("#LastName").val()
        });
        return person;
    }


    $("#searchNamesFormGen").submit(function (event) {
        event.preventDefault();
        console.log("Test");
        
        var search_url = "";
        var form_fname = $("#FirstName").val();
        var form_lname = $("#LastName").val();

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
                console.log("data found!!!" + $(data).find("searchResult").size());// + data.find("firstname").text());
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
                        $(recol).attr("href", "home.html?recid=" + recid);
                        foreachresRecCol.find("#fnameCol").text($(this).find("firstname").text());
                        foreachresRecCol.find("#lnameCol").text($(this).find("lastname").text());
                        foreachresRecCol.find("#titlecol").text($(this).find("title").text());
                        $(resclone).append(foreachresRecCol);
                    });

                    $("#resultDivContainer").append(resclone);
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
            }
        });
        
    })

})

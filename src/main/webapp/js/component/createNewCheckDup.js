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
        console.log("Test");
        $("#searchResultDiv").remove();

        searchByPersonNames(getSearchFormData(), function (data) {
            console.log("1");
            
            /*
            var xmlDoc = $.parseXML(data);
            console.log("2");
            var xmlDoc = data;
            var x = xmlDoc.getElementsByTagName("firstName")[1].childNodes[0].nodeValue;
            console.log("2" + x);

            var yyy = data.getElementsByTagName("person");
            var txt = "";
            var i;
            console.log("3" + yyy.length);
            for (i = 0; i < yyy.length; i++) {
                console.log("3.5" + yyy[i].getElementsByTagName("firstName").nodeName);
                var v;
                for (v = 0; v < yyy[i].childNodes.length; v++) {
                    var elem = yyy[i].childNodes[v];
                    console.log("elem name: " + elem.nodeName);
                    //console.log("elem value: " + elem.childNodes[0].nodeValue);
                }
                //txt = yyy[i].nodeName + ": " + yyy[i].childNodes[0].nodeValue;
                //console.log("4" + txt);
            }
            */
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
        /*
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
         */

    })

})

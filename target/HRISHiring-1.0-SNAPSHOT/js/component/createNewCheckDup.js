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

    //$("#batchUpload").click(function (e) {
    var fileInputCSV = document.getElementById('batchUpload'); 
    fileInputCSV.addEventListener('change', function (e) {
        console.log("batch upload")
        
        var file = e.target.files[0];
        var csvParser = new SimpleExcel.Parser.CSV();
        csvParser.setDelimiter(',');
        csvParser.loadFile(file, function () {

            // draw HTML table based on sheet data
            var sheet = csvParser.getSheet();
            var table = document.getElementById('result');
            table.innerHTML = "";
            sheet.forEach(function (el, i) {
                var row = document.createElement('tr');
                el.forEach(function (el, i) {
                    var cell = document.createElement('td');
                    cell.innerHTML = el.value;
                    row.appendChild(cell);
                });
                table.appendChild(row);
            });

            // create button to export as TSV
            var btnSave = document.getElementById('fileExport');
            btnSave.hidden = false;
            btnSave.value = 'Save as TSV file ->';
            document.body.appendChild(btnSave);

            // export when button clicked
            btnSave.addEventListener('click', function (e) {
                var tsvWriter = new SimpleExcel.Writer.TSV();
                tsvWriter.insertSheet(csvParser.getSheet(1));
                tsvWriter.saveFile();
            });

            // print to console just for quick testing
            console.log(csvParser.getSheet(1));
            console.log(csvParser.getSheet(1).getRow(1));
            console.log(csvParser.getSheet(1).getColumn(2));
            console.log(csvParser.getSheet(1).getCell(3, 1));
            console.log(csvParser.getSheet(1).getCell(2, 3).value);
        });
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

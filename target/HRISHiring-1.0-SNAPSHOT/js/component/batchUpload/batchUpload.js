/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var continueDataSheet = new SimpleExcel.Sheet();
var table_colsize = 10;

$(document).ready(function () {

    console.log("batch upload");
    checkCredential();

    document.getElementById('fform').reset();

    var Cell = SimpleExcel.Cell;

    var table = document.getElementById('result').cloneNode(true);
    var tableerr = document.getElementById('errorTable').cloneNode(true);

    $("#section1").find("#result").remove();
    $("#section1").find("#errorTable").remove();

    //$("#batchUpload").click(function (e) {
    var fileInputCSV = document.getElementById('batchUpload');
    fileInputCSV.addEventListener('change', function (e) {

        var file = e.target.files[0];
        var csvParser = new SimpleExcel.Parser.CSV();
        csvParser.setDelimiter(',');

        csvParser.loadFile(file, function () {

            $(tableerr).find("#errorBody").empty();
            $(table).find("#resultBody").empty();
            $("#fform").remove();
            $("#flname").text(fileInputCSV.value);


            // draw HTML table based on sheet data
            var sheet = csvParser.getSheet();
            //console.log("raw sheet stringify " + JSON.stringify(sheet));

            sheet.forEach(function (el, i) {
                var row = document.createElement('tr');
                if (el.length < table_colsize) {
                    var x = 0;
                    el.forEach(function (el, i) {
                        if (x == table_colsize) {
                            return;
                        }
                        var cell = document.createElement('td');
                        cell.innerHTML = el.value;
                        //console.log("el: " + el.value);
                        row.appendChild(cell);
                        x++;
                    });
                    $(tableerr).find("#errorBody").append(row);
                } else {
                    //continueData = continueData + el;
                    continueDataSheet.insertRecord(el);

                    //continueDataSheet = continueDataSheet + 
                    var x = 0;
                    el.forEach(function (el, i) {
                        if (x == table_colsize) {
                            return;
                        }
                        var cell = document.createElement('td');
                        cell.innerHTML = el.value;
                        //console.log("el: " + el.value);
                        row.appendChild(cell);
                        x++;
                    });
                    $(table).find("#resultBody").append(row);
                }
            })

            $("#section1").append(table);
            $("#section1").append(tableerr);
            $("#contBut").click(function () {
                //console.log("Continue button clisked - stringyfy-" + JSON.stringify(continueDataSheet));

                $("#section1").load("htmlcomponents/batchUpload/validateUpload.html", function () {
                    $.getScript("js/component/batchUpload/validateUpload.js");
                });
            })

            // print to console just for quick testing
            /*
             console.log(csvParser.getSheet(1));
             console.log(csvParser.getSheet(1).getRow(1).toString());
             console.log(csvParser.getSheet(1).getColumn(2).toString());
             console.log(csvParser.getSheet(1).getCell(3, 1));
             console.log(csvParser.getSheet(1).getCell(2, 3).value);
             */
        });
    })


})
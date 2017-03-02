/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var continueDataSheet;

$(document).ready(function () {

    console.log("batch upload");

    var Cell = SimpleExcel.Cell;

    continueDataSheet = new SimpleExcel.Sheet();//[{test: "",me:""},{test:"",me:""}];

    var table = document.getElementById('result').cloneNode(true);
    var tableerr = document.getElementById('errorTable').cloneNode(true);
    $("#section1").find("#result").remove();
    $("#section1").find("#errorTable").remove();

    //$("#batchUpload").click(function (e) {
    var fileInputCSV = document.getElementById('batchUpload');
    fileInputCSV.addEventListener('change', function (e) {
        console.log("loading...");

        var file = e.target.files[0];
        var csvParser = new SimpleExcel.Parser.CSV();
        csvParser.setDelimiter(',');

        csvParser.loadFile(file, function () {

            // draw HTML table based on sheet data
            var sheet = csvParser.getSheet();

            //table.innerHTML = "";

            console.log("raw sheet stringify "+JSON.stringify(sheet));
            
            sheet.forEach(function (el, i) {
                var row = document.createElement('tr');
                if (el.length != 4) {
                    el.forEach(function (el, i) {
                        var cell = document.createElement('td');
                        cell.innerHTML = el.value;
                        console.log("el: " + el.value);
                        row.appendChild(cell);
                    });
                    $(tableerr).find("#errorBody").append(row);
                } else {
                    //continueData = continueData + el;
                    continueDataSheet.insertRecord(el);
                    
                    //continueDataSheet = continueDataSheet + 
                            
                    el.forEach(function (el, i) {
                        var cell = document.createElement('td');
                        cell.innerHTML = el.value;
                        console.log("el: " + el.value);
                        row.appendChild(cell);
                    });
                    $(table).find("#resultBody").append(row);
                }
            })

            $("#section1").append(table);
            $("#section1").append(tableerr);
            $("#contBut").click(function () {
                console.log("Continue button clisked - stringyfy-" + JSON.stringify(continueDataSheet));

                $("#section1").load("htmlcomponents/batchUpload/validateUpload.html", function () {
                    $.getScript("js/component/batchUpload/validateUpload.js");
                });
            })

            // print to console just for quick testing
            console.log(csvParser.getSheet(1));
            console.log(csvParser.getSheet(1).getRow(1).toString());
            console.log(csvParser.getSheet(1).getColumn(2).toString());
            console.log(csvParser.getSheet(1).getCell(3, 1));
            console.log(csvParser.getSheet(1).getCell(2, 3).value);
        });
    })


})
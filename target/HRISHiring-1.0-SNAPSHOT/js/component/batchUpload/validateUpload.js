/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    console.log("validate upload");
    console.log("data - " + JSON.stringify(continueDataSheet));

    var table = document.getElementById('result').cloneNode(true);

    $("#section1").find("#result").remove();

    function validateNames(form_fname, form_lname, callback) {//method should validate names and return id if exist or null
        var searchpid = null
        var search_url = "";
console.log(" f and l names : "+form_fname+"-"+form_lname);
        if (form_fname != "" && form_lname == "") {
            //console.log("search for f name");
            search_url = url_searchByNames + form_fname;
            
        }
        if (form_lname != "" && form_fname == "") {
            search_url = "";
        }
        if (form_fname != "" && form_lname != "") {
            console.log("search for f and l name");
            search_url = url_searchByNames + form_fname;
            ////temp logic - shouuld look with lastname no service for that yet
        }
        console.log("search url: "+search_url);
        $.ajax({
            type: 'GET',
            url: search_url,
            success: function (data) {
                var perid = "";
                console.log("pid "+$(data).first().find("personID").text());
                $(data).find("searchResults").each(function(){
                    console.log("pid ---"+$(this).find("personID").text());
                })
                if ($(data).find("personID").text() != "") {
                    perid = $(data).find("personID").text();
                    console.log("found dups! perid " + perid);
                    if (callback && typeof (callback) === "function") {
                        //do something here from your call back function
                        //console.log("Calling the callback inside the function getActivities...")
                        callback(perid);
                    }
                } else {
                    if (callback && typeof (callback) === "function") {
                        //do something here from your call back function
                        //console.log("Calling the callback inside the function getActivities...")
                        callback(perid);
                    }
                }
            }
        })
    }


    continueDataSheet["records"].forEach(function (el, i) {
        var row = document.createElement('tr');
        
        validateNames(el[0].value, el[1].value, function(perid){
            console.log("perid from validation: "+perid);
            
        })

        //continueData = continueData + el;
        var persondata = {
            firstName: el[0].value,
            lastname: el[1].value,
            company: el[2].value,
            title: el[3].value
        };

        var jqdata = {};

        var contactdata = {};

        console.log("data:" + JSON.stringify(persondata));

        el.forEach(function (el, i) {
            var cell = document.createElement('td');
            cell.innerHTML = el.value;
            console.log("el: " + el.value);
            row.appendChild(cell);
        });
        $(table).find("#resultBody").append(row);

    })
    $("#section1").append(table);

})
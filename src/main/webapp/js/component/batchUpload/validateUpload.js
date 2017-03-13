/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    checkCredential(function () {
        console.log("credential = " + credentialID);
    });

    console.log("validate upload - " + credentialID);
    console.log("data - " + JSON.stringify(continueDataSheet));

    var continueforJobMatch = new SimpleExcel.Sheet();
    var duplicateRec = new SimpleExcel.Sheet();

    var table = document.getElementById('resultTable').cloneNode(true);
    var duptable = document.getElementById('duplicateTable').cloneNode(true);

    $("#section1").find("#resultTable").remove();
    $("#section1").find("#duplicateTable").remove();

    init(function () {

    });

    function init(callback) {
        continueDataSheet["records"].forEach(function (el, i) {
            var row = document.createElement('tr');

            getPersonByName(el[0].value, function (data) {
                var perid = $(data).find("idPerson").text();
                console.log("perid from validation: " + perid);
                if (perid == "") {
                    console.log("unique");
                    //setTimeout(function () {
                        addRecord(el, function () {
                            //add to the table
                            var x = 0;
                            el.forEach(function (el, i) {
                                if (x = table_colsize) {
                                    return;
                                }
                                var cell = document.createElement('td');
                                cell.innerHTML = el.value;
                                console.log("el: " + el.value);
                                row.appendChild(cell);
                                x++;
                            });
                            continueforJobMatch.insertRecord(el);
                            $(table).find("#resultBody").append(row);
                            console.log("here...........................");
                        });
                    //}, 1000);

                } else {
                    console.log("dups");
                    var tcomp = el[8].value;
                    var tjob = el[9].value;
                    console.log(tcomp.length + "---------" + tjob.length);
                    if (tcomp != "" && tjob != "") {
                        console.log("passedddddd");
                        addEndorsement(el, perid, function (data) {
                            console.log("endorsement saved!")
                            var x = 0;
                            el.forEach(function (el, i) {
                                if (x == table_colsize) {
                                    return;
                                }
                                var cell = document.createElement('td');
                                cell.innerHTML = el.value;
                                console.log("el: " + el.value);
                                row.appendChild(cell);
                                x++;
                            });
                        });
                    }
                    duplicateRec.insertRecord(el);
                    $(duptable).find("#resultBody").append(row);
                }
            });

        });

        $("#tab_panel").append(table);
        $("#tab_panel").append(duptable);
    }

    //change in requirement - will be doing validation by full name instead of firstname and lastname
    function validateNames(form_fname, form_lname, callback) {//method should validate names and return id if exist or null
        var searchpid = null
        var search_url = "";
        console.log(" f and l names : " + form_fname + "-" + form_lname);
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
        console.log("search url: " + search_url);
        $.ajax({
            type: 'GET',
            url: search_url,
            success: function (data) {
                var perid = "";
                //console.log("pid "+$(data).first().find("personID").text());
                $(data).find("searchResult").each(function () {
                    console.log("pid ---" + $(this).find("personID").text());
                    perid = $(this).find("personID").text();
                    if (perid.length > 0) {
                        return false;
                    }
                })

                if (callback && typeof (callback) === "function") {
                    //do something here from your call back function
                    //console.log("Calling the callback inside the function getActivities...")
                    callback(perid);
                }

            }
        })
    }

    function addRecord(el, callback) {
        //continueData = continueData + el;
        var uploadpersondata = JSON.stringify({
            name: el[0].value,
            firstName: el[1].value,
            lastName: el[2].value
        });
        console.log("Person to add: " + JSON.stringify(uploadpersondata));

        if (JSON.stringify(uploadpersondata).length > 0) {
            saveProfile(uploadpersondata, function (data) {
                var pidq = $(data).find("idPerson").text();
                var jqdata = JSON.stringify({
                    personId: {idPerson: pidq},
                    company: el[3].value,
                    jobTitle: el[4].value,
                    yrsOfExperience: el[5].value
                });
                console.log("jobqualification to add: " + JSON.stringify(jqdata));
                var jqdataap = el[3].value + el[4].value + el[5].value;

                if (jqdataap.length > 0) {
                    saveJobQualification(jqdata, function () {
                        console.log("Job qualification saved!");
                    });
                }

                var upload_contactdata = JSON.stringify({
                    personidPerson: {idPerson: pidq},
                    cellphoneNum: el[6].value,
                    email: el[7].value
                });

                console.log("Contact to add: " + JSON.stringify(upload_contactdata));
                var condataap = el[6].value + el[7].value;
                if (condataap.length > 0) {
                    saveContact(upload_contactdata, function () {
                        console.log("Contacts successfully saved");
                    });
                }
                getActivityEntityID1(pidq, function (entid) {
                    activityEntityID = entid;//need optimization
                    createActivity(function () {
                        if (callback && typeof (callback) === "function") {
                            //do something here from your call back function
                            //console.log("Calling the callback inside the function getActivities...")
                            callback();
                        }
                    });
                });

            });
        }
        console.log("data:" + JSON.stringify(persondata));
    }

function test(){
    console.log("mmmmeeee");
}
    function addEndorsement(el, persID, callback) {
        console.log("debugggg1");
        var endorsement = JSON.stringify({
            companyIdclient: {idclient: el[8].value},
            personidPerson: {idPerson: persID},
            jobIdjobpk: {idjobpk: el[9].value}
        });
        console.log("adding endorsement: "+endorsement);
        $.ajax({
            type: 'PUT',
            url: url_addCandidatesUnique,
            contentType: 'application/json',
            data: endorsement,
            success: function (data) {
                if (callback && typeof (callback) === "function") {
                    callback(data);
                }
            },
            error: function () {
                showAlert("Application Error! Please contact system admin.");
            }
        });
    }

    function createActivity(callback) {
        var activityData = JSON.stringify({
            //idSourcingActivities: working_activity_id,
            createdBy: credentialPersonID,
            updatedBy: credentialPersonID,
            description: "Initial creation of record from batch upload.",
            nsbActivityStatusTp: {idactivityStatus: "1"},
            nsbActivityTp: {idActivityTp: "1"},
            nsbEntityActivities: {idpersonactivities: activityEntityID}
        });

        $.ajax({
            type: 'POST',
            url: save_activities_url,
            contentType: 'application/json',
            data: activityData,
            success: function (data) {
                console.log("new activity cretaed");
                if (callback && typeof (callback) === "function") {
                    callback();
                }
            },
            error: function () {
                console.log("ERROR ENCOUNTERED CREATING ACTIVITY");
            }
        });
    }




})
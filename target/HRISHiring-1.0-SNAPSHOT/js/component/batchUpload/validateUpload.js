/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var table_result_colsize = 11;

    checkCredential(function () {
        console.log("credential = " + credentialID);
        init();
    });

    //$("#section1").find("#resultTable").remove();
    //$("#section1").find("#duplicateTable").remove();


    function init() {
        var continueforJobMatch = new SimpleExcel.Sheet();
        var duplicateRec = new SimpleExcel.Sheet();

        var rectable = $("#resultTable").find("#resultBody");//.cloneNode(true);
        var recduptable = $("#duplicateTable").find("#resultBody");//.cloneNode(true);
        console.log("test 11111");
        continueDataSheet["records"].forEach(function (el, i) {
            var row = document.createElement('tr');

            getPersonByName(el[0].value, function (data) {
                var perid = $(data).find("idPerson").text();
                if (perid == "") {
                    console.log("unique");

                    addRecord(el, function () {
                        //add to the table
                        var x = 0;
                        el.forEach(function (el, i) {
                            if (x == table_result_colsize) {
                                return;
                            }
                            var cell = document.createElement('td');
                            if (x == table_result_colsize - 1) {
                                cell.innerHTML = "Added";
                                console.log("added");
                            } else {
                                cell.innerHTML = el.value;
                                console.log("value");
                            }
                            row.appendChild(cell);
                            rectable.append(row);
                            //rectable.append("test");
                            x++;
                        });
                        continueforJobMatch.insertRecord(el);
                    });

                } else {
                    console.log("dups!!! -" + perid);
                    var tcomp = el[8].value;
                    var tjob = el[9].value;
                    if (tcomp != "" && tjob != "") {
                        console.log("With endorsement data...");

                        addEndorsement(el, perid, function (data) {
                            console.log("endorsement saved!" + $(data).find("idendorsement").text())
                            if ($(data).find("idendorsement").text() != "") {
                                console.log("endorsement ID created!");
                                var x = 0;
                                el.forEach(function (el, i) {
                                    
                                    if (x == table_result_colsize) {
                                        return;
                                    }
                                    var cell = document.createElement('td');
                                    if (x == table_result_colsize - 1) {
                                        cell.innerHTML = "Endorsement added";
                                        console.log("Endorsement added in cell");
                                    } else {
                                        cell.innerHTML = el.value;
                                        console.log("value in x="+x);
                                    }
                                    
                                    cell.innerHTML = el.value;
                                    row.appendChild(cell);
                                    rectable.append(row);
                                    x++;
                                });
                            } else {
                                console.log("NO endorsement ID created!");
                                var x = 0;
                                el.forEach(function (el, i) {

                                    if (x == table_result_colsize) {
                                        return;
                                    }
                                    var cell = document.createElement('td');
                                    if (x == table_result_colsize - 1) {
                                        cell.innerHTML = "Unable to add endorsement.";
                                        console.log("Unable to add endorsement.");
                                    } else {
                                        cell.innerHTML = el.value;
                                        console.log("value in x="+x);
                                    }
                                    
                                    cell.innerHTML = el.value;
                                    row.appendChild(cell);
                                    recduptable.append(row);
                                    x++;
                                });
                            }
                        });
                    } else {
                        console.log("asfadfasdfadfaf");
                        var x = 0;
                        el.forEach(function (el, i) {

                            if (x == table_result_colsize) {
                                return;
                            }
                            var cell = document.createElement('td');
                            if (x == table_result_colsize - 1) {
                                cell.innerHTML = "Duplicated Record";
                                console.log("added");
                            } else {
                                cell.innerHTML = el.value;
                                console.log("value");
                            }
                            
                            cell.innerHTML = el.value;
                            row.appendChild(cell);
                            recduptable.append(row);
                            x++;
                        });
                    }
                    duplicateRec.insertRecord(el);

                }
            });

        });

        //$("#tab_panel").append(table);
        //$("#tab_panel").append(duptable);
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
                });
                if (callback && typeof (callback) === "function") {
                    //do something here from your call back function
                    //console.log("Calling the callback inside the function getActivities...")
                    callback(perid);
                }
            }
        });
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

                console.log("jobqualification to add: " + JSON.stringify(jqdata));
                var jqdataap = el[3].value + el[4].value + el[5].value;
                if (jqdataap.length > 0) {
                    var jqdata = JSON.stringify({
                        personId: {idPerson: pidq},
                        company: el[3].value,
                        jobTitle: el[4].value,
                        yrsOfExperience: el[5].value
                    });
                    saveJobQualification(jqdata, function () {
                        console.log("Job qualification saved!");
                    });
                }

                console.log("Contact to add: " + JSON.stringify(upload_contactdata));
                var condataap = el[6].value + el[7].value;
                if (condataap.length > 0) {
                    var upload_contactdata = JSON.stringify({
                        personidPerson: {idPerson: pidq},
                        cellphoneNum: el[6].value,
                        email: el[7].value
                    });
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

    function addEndorsement(el, persID, callback) {
        console.log("addEndorsement() --->");
        var endorsement = JSON.stringify({
            companyIdclient: {idclient: el[8].value},
            personidPerson: {idPerson: persID},
            jobIdjobpk: {idjobpk: el[9].value}
        });
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
            updatedByName: credentialPersonName,
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
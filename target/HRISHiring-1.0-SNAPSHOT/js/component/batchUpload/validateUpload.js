/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var table_result_colsize = 11;

    var continueforJobMatch = new SimpleExcel.Sheet();
    var duplicateRec = new SimpleExcel.Parser.CSV();
    duplicateRec.setDelimiter(',');

    init();

    //$("#section1").find("#resultTable").remove();
    //$("#section1").find("#duplicateTable").remove();

    function withEndorsement(el) {
        var tcomp = el[8].value;
        var tjob = el[9].value;
        if (tcomp != "" && tjob != "") {
            return true;
        } else {
            return false;
        }
    }

    function addRecToTable(el, msgsadd, table) {
        var row = document.createElement('tr');
        el.forEach(function (el, i) {
            if (i == table_result_colsize) {
                return;
            }
            var cell = document.createElement('td');
            if (i == table_result_colsize - 1) {
                cell.innerHTML = msgsadd;
            } else {
                cell.innerHTML = el.value;
            }
            row.appendChild(cell);
            table.append(row);
        });
        //continueforJobMatch.insertRecord(el);
    }


    function init() {

        var rectable = $("#resultTable").find("#resultBody");
        var recduptable = $("#duplicateTable").find("#resultBody");//.cloneNode(true);
        
        $("#dp").text(continueDataSheet["records"].length);

        continueDataSheet["records"].forEach(function (el, i) {
            //var row = document.createElement('tr');
            
            getPersonByName(el[0].value, function (data) {
                var perid = $(data).find("idPerson").first().text();
                if (perid == "") {
                    console.log("unique with endorsement: " + withEndorsement(el));

                    addRecord(el, function (peridcreated) {
                        var msgsadd = "Added Record ID=" + peridcreated;
                        if (withEndorsement(el)) {
                            addEndorsement(el, peridcreated, function (data) {

                            });
                        }
                        addRecToTable(el, msgsadd, rectable);
                    });
                } else {
                    console.log("dups!!! -" + perid);
                    if (withEndorsement(el)) {
                        console.log("With endorsement data...");
                        addEndorsement(el, perid, function (data) {
                            var endorseid = $(data).find("idendorsement").text();
                            console.log("Endorsement ID created!-" + endorseid + "-");
                            if (endorseid != "") {
                                addRecToTable(el, "Dup RecID:" + perid + "; Added Endorsement (ID=" + endorseid + ")", rectable);
                            } else {
                                console.log("NO endorsement ID created! Due to - " + $(data).text());
                                addRecToTable(el, "Dup Rec ID:" + perid + "; Unable to add endorsement.", recduptable);
                                duplicateRec.insertRecord(el);
                            }
                        });
                    } else {
                        addRecToTable(el, "Dup Rec ID:" + perid, recduptable);
                        duplicateRec.insertRecord(el);
                    }


                }
            });
            
                $("#cp").text(i+1);
           
            
        });


        $("#contBut").click(function () {
            var tsvWriter = new SimpleExcel.Writer.TSV();
            duplicateRec.setDelimiter(',');
            tsvWriter.insertSheet(duplicateRec.getSheet(1));
            tsvWriter.saveFile();
        });

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
        //console.log("Person to add: " + JSON.stringify(uploadpersondata));

        if (JSON.stringify(uploadpersondata).length > 0) {
            saveProfile(uploadpersondata, function (data) {
                var pidq = $(data).find("idPerson").text();
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
                            callback(pidq);
                        }
                    });
                });

            });
        }
    }

    function addEndorsement(el, persID, callback) {
        console.log("addEndorsement() --->");
        var endorsement = JSON.stringify({
            companyIdclient: {idclient: el[8].value},
            personidPerson: {idPerson: persID},
            jobIdjobpk: {idjobpk: el[9].value}
        });
        console.log(endorsement);
        $.ajax({
            type: 'PUT',
            url: url_addCandidatesUnique,
            contentType: 'application/json',
            data: endorsement,
            success: function (data) {
                console.log("added endorsement" + $(data).toString());
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
            updatedByName: credentialPersonflname,
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
                //console.log("new activity cretaed");
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
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    console.log("loaded generalInformation" + searchNamesForm_fname);
    //showAlert("This is my message!!"+searchNamesForm_fname);
    $("#FirstName").val(searchNamesForm_fname);
    $("#LastName").val(searchNamesForm_lname);


    function getNamesFormData() {

    }

    function getNamesFormDataSize() {
        var str = $("#FirstName").val() + $("#LastName").val() + $("#name").val();
        return str.length;
    }
    function getContactsFormDataSize() {
        var str = $("#mobilenum").val() + $("#email").val();
        return str.length;
    }
    function getJobQualificationDataSize() {
        var str = $("#title").val() + $("#company").val() + $("#yrsOfExp").val() + $("#skills").val();
        return str.length;
    }


    $("#newCandidateForm").submit(function (event) {
        event.preventDefault();
        showLoader();
        saveProfileForm(function () {
            console.log("Save profile form done");
            saveContactForm(function () {
                console.log("Save contact form done");
                getActivityEntityID(function () {
                    createActivity(function () {
                        createQualification(function(){
                            console.log("Save new activty");
                            hideLoader();
                            initWithProfile();
                        })
                        console.log("Save new activty");
                    });
                })
            })
        })
    })

    function createQualification(callback) {
        if (getJobQualificationDataSize() > 0) {
            var qualificationData = JSON.stringify({
                jobTitle: $("#title").val(),
                company: $("#company").val(),
                yrsOfExperience: $("#yrsOfExp").val(),
                skills: $("#skills").val()
            });
            console.log("Data for new job qualification: " + qualificationData);
            saveJobQualification(qualificationData, function () {
                console.log("Save JobQualification.");
                if (callback && typeof (callback) === "function") {
                    callback();
                }
            })
        } else {
            if (callback && typeof (callback) === "function") {
                callback();
            }
        }

    }

    function createActivity(callback) {
        var activityData = JSON.stringify({
            //idSourcingActivities: working_activity_id,
            createdBy: credentialPersonID,
            updatedBy: credentialPersonID,
            updatedByName: credentialPersonName,
            description: "Initial creation of record.",
            nsbActivityStatusTp: {idactivityStatus: "1"},
            nsbActivityTp: {idActivityTp: "1"},
            nsbEntityActivities: {idpersonactivities: activityEntityID}
        });
        console.log("Data for new activity: " + activityData);

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

    function saveContactForm(callback) {
        if (getContactsFormDataSize() > 0) {
            console.log("contacts present");
            var contactdata = JSON.stringify({
                contactNum: $("#mobilenum").val(),
                email: $("#email").val()
            });
            console.log("contact form data" + contactdata);
            saveContact(contactdata, function () {
                console.log("save contact");
                if (callback && typeof (callback) === "function") {
                    callback();
                }
            });
        } else {
            console.log("No contact info... skipping");
            if (callback && typeof (callback) === "function") {
                callback();
            }
        }
    }

    function saveProfileForm(callback) {
        if (getNamesFormDataSize() > 0) {
            var profiledata = JSON.stringify({
                firstName: $("#FirstName").val(),
                lastName: $("#LastName").val(),
                lastUpdatePersonID: credentialPersonID,
                name: $("#name").val()
            });

            saveProfile(profiledata, function () {
                console.log("saveProfile done")
                if (callback && typeof (callback) === "function") {
                    callback();
                }
            })
        } else {
            if (callback && typeof (callback) === "function") {
                callback();
            }
        }
    }


})
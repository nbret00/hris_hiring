/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    init();

    var contactFormAction = "Update";
    var jobQualificationFormAction = "Update";

    function init() {
        showLoader();
        getPersonalProfile(working_person_id, function () {
            prepPersonProfileForm(personProfile, function () {
                getContactByPersonID(function () {
                    prepContactForm(contactInfo, function () {
                        getJobQualificationByPersonID(function () {
                            prepJobQualificationForm(jobQualification, function () {
                                hideLoader();

                            });
                        });
                    });
                });
            });
        });
    }

    $(updateGeneralInfoForm).submit(function (event) {
        event.preventDefault();
        showLoader();
        updateProfileForm(function () {
            if (contactFormAction == "Update") {
                updateContactForm(function () {
                    saveUpdateQualificationHandler();
                })
            } else {
                saveContact(getContactFormData(),function(){
                    saveUpdateQualificationHandler();
                })
            }
        });
    })

    function saveUpdateQualificationHandler() {
        if (jobQualificationFormAction == "Update") {
            updateQualificationForm(function () {
                hideLoader();
                showAlert("Successfully update general information.");
            })
        } else {
            saveJobQualification(getQualificationFormData(), function () {
                hideLoader();
                showAlert("Successfully update general information.");
            })
        }
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

    function prepJobQualificationForm(jobQualificationXMLdata, callback) {
        console.log("prepJobQualificationForm--->>>");
        if ($(jobQualificationXMLdata).length == 0) {
            jobQualificationFormAction = "Save";
        }
        $(jobQualificationXMLdata).find("jobQualification").each(function () {
            document.getElementById("title").value = ifnull($(this).find("jobTitle").text());
            document.getElementById("company").value = ifnull($(this).find("company").text());
            document.getElementById("yrsOfExp").value = ifnull($(this).find("yrsOfExperience").text());
            document.getElementById("skills").value = ifnull($(this).find("skills").text());
        });
        if (callback && typeof (callback) === "function") {
            console.log("Calling the callback inside the function...");
            callback();
        }
        console.log("<<---prepJobQualificationForm");
    }

    function prepPersonProfileForm(personXMLdata, callback) {
        console.log("prepPersonProfileform--->>>");
        $(personXMLdata).find("person").each(function () {
            document.getElementById("name").value = ifnull($(this).find("name").text());
            document.getElementById("LastName").value = ifnull($(this).find("lastName").text());
            document.getElementById("FirstName").value = ifnull($(this).find("firstName").text());
        })
        //document.getElementById("loader_comp"). setAttribute("class","loader-sm overlay_center");
        if (callback && typeof (callback) === "function") {
            //do something here from your call back function
            console.log("Calling the callback inside the function...");
            callback();
        }
        ;
        console.log("<<---prepPersonProfileForm");
    }

    function prepContactForm(contactInfoXMLdata, callback) {
        console.log("prepContactForm--->>>");
        if ($(contactInfoXMLdata).length == 0) {
            contactFormAction = "Save";
        }
        $(contactInfoXMLdata).find("contact").each(function () {
            document.getElementById("mobilenum").value = ifnull($(this).find("cellphoneNum").text());
            document.getElementById("email").value = ifnull($(this).find("email").text());
        });
        if (callback && typeof (callback) === "function") {
            //do something here from your call back function
            console.log("Calling the callback inside the function...");
            callback();
        }
        ;
        console.log("<<---prepContactForm");
    }
    ;

    function getQualificationFormData() {
        var qualificationData = JSON.stringify({
            idJobQualification: working_jobqualification_id,
            jobTitle: $("#title").val(),
            company: $("#company").val(),
            yrsOfExperience: $("#yrsOfExp").val(),
            skills: $("#skills").val()
        });
        return qualificationData;
    }
    function updateQualificationForm(callback) {
        if (getJobQualificationDataSize() > 0) {
            updateJobQualification(getQualificationFormData(), function () {
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

    function updateActivityForm(callback) {
        var activityData = JSON.stringify({
            //idSourcingActivities: working_activity_id,
            createdBy: credentialID,
            updatedBy: credentialID,
            description: "Initial creation of record.",
            nsbActivityStatusTp: {idactivityStatus: "1"},
            nsbActivityTp: {idActivityTp: "1"},
            nsbEntityActivities: {idpersonactivities: activityEntityID}
        });
        console.log("Data for new activity: " + activityData);
        updateActivity(activityData, function () {
            if (callback && typeof (callback) === "function") {
                callback();
            }
        })

    }
    function getContactFormData() {
        var contactdata = JSON.stringify({
            idcontact: working_contact_id,
            cellphoneNum: $("#mobilenum").val(),
            email: $("#email").val()
        });
        return contactdata;
    }
    function updateContactForm(callback) {
        if (getContactsFormDataSize() > 0) {
            updateContact(getContactFormData(), function () {
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

    function updateProfileForm(callback) {
        if (getNamesFormDataSize() > 0) {
            var profiledata = JSON.stringify({
                firstName: $("#FirstName").val(),
                lastName: $("#LastName").val(),
                lastUpdatePersonID: credentialPersonID,
                name: $("#name").val()
            });

            updateProfile(profiledata, function () {
                console.log("updateProfile done")
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


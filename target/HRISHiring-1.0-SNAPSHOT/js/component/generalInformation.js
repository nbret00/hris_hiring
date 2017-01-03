/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
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

    function prepJobQualificationForm(jobQualificationXMLdata, callback) {
        console.log("prepJobQualificationForm--->>>");
        $(jobQualificationXMLdata).find("jobQualification").each(function () {
            //TODO need to add company and status(hris status)
            document.getElementById("title").value = ifnull($(this).find("jobTitle").text());
            document.getElementById("yrsOfExp").value = ifnull($(this).find("yrsOfExperience").text());
            document.getElementById("skills").value = ifnull($(this).find("skills").text());
        });
        if (callback && typeof (callback) === "function") {
            //do something here from your call back function
            console.log("Calling the callback inside the function...");
            callback();
        }
        ;
        console.log("<<---prepJobQualificationForm");
    }
    ;

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

    $("#panel").remove();
    $("#rem_container").load("htmlcomponents/remarks.html", function () {


    });
})


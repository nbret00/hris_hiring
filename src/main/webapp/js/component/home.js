

$(document).ready(function () {

    
     console.log("document.URL : "+credentialPersonID);
    /*
     console.log("document.location.href : "+document.location.href);
     console.log("document.location.origin : "+document.location.origin);
     console.log("document.location.hostname : "+document.location.hostname);
     console.log("document.location.host : "+document.location.host);
     console.log("document.location.pathname : "+document.location.pathname);
     console.log("location.hostname: "+location.hostname);
     console.log("document.domain: "+document.domain);
     console.log("window.location.hostname: "+window.location.hostname);
     //alert("working_person_id" + working_person_id);
     */


    var working_sourcing_id = "";
    var cand_name = "";

    var sourcing = null;
    var searchStr = null;

    checkCredential(function () {
        var recid = GetURLParameter("recid");
        if (recid !== undefined) {
            getPersonalProfile(recid, function () {
                initWithProfile();
            });
        } else {
            init();
        }
    });


    //load the default panel and data
    function init() {
        $("#loginas").text(credentialPersonflname+" ("+credentialPersonID+")");
        $("#section1").load("htmlcomponents/createNewCheckDup.html", function () {
            $.getScript("js/component/createNewCheckDup.js");
        });

        $("#leftnavbar").addClass("disableddiv");
    }


    //submit of search
    $("#searchForm").submit(function (event) {
        //alert("clicked!");
        event.preventDefault();
        searchStr = null;
        searchStr = document.getElementById("searchStr").value; //$("#searchStr").val();
        console.log("serching for: |" + searchStr + "|");
        if (searchStr === null || searchStr == '') {
            showAlert("Provide Record # to search.");
            //refreshing all global variable
        } else {

            $("#searchStr").val(""); //clear the search field
            working_person_id = "";
            working_jobqualification_id = "";
            working_contact_id = "";
            working_sourcing_id = "";
            cand_name = "";
            personProfile = null;
            jobQualification = null;
            contactInfo = null;
            activities = null;
            sourcing = null;
            activityEntity = null;

            if (isNaN(searchStr)) {
                showAlert("Rec # should be a number.");
            } else {
                showLoader();
                //document.getElementById("activePerson").innerHTML = "Searching...";
                getPersonalProfile(searchStr, function () {
                    //setTimeout(alert("Searching record # " + searchStr), 10000);
                    if (working_person_id == null || working_person_id == "") {
                        showAlert("Search for a candidate - No record found.")
                    } else {
                        initWithProfile();
                    }
                    hideLoader();
                });
            }
            ;
        }
        ;
    });

//------------------------------------General Info
    $("#generalinfopage").on("click", function (e) {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/generalInformation.html", function () {
            $.getScript("js/component/generalInformation.js");
        });
    });
    
//------------------------------------Job match
    $("#jobmatch").on("click", function (e) {
        console.log("test1111");
        $("#panel").remove();
        $("#section1").load("htmlcomponents/candjobmatch/candjobsmatch.html", function () {
            $.getScript("js/component/candjobmatch/candjobsmatch.js");
        });
    });
//----------------------------------------------------- Person Profile

    //nav bars
    $("#canprofile").on("click", function (e) {
        showLoader();
        showPersonalProfileForm();
    });
    function showPersonalProfileForm() {
        $("#panel").remove();
        $("#section1").load("htmlcomponents/personalprofile.html", function () {
            $('.datepicker').datepicker({
                startDate: "09/05/2004"});
            if (personProfile == null) {
                console.log("personPrfile is null");
                document.getElementById("personProfileBut").innerHTML = "Save";
                personProfileSaveUpdateHandler();
                hideLoader();
            } else {
                console.log("PersonProfile not null " + personProfile);
                prepPersonProfileForm(personProfile);
                personProfileSaveUpdateHandler();
            }
        });
    }
    ;
    function prepPersonProfileForm(personXMLdata) {
        console.log("prepPersonProfileform--->>>");
        $(personXMLdata).find("person").each(function () {
            document.getElementById("Name").value = ifnull($(this).find("name").text());
            document.getElementById("LastName").value = ifnull($(this).find("lastName").text());
            document.getElementById("FirstName").value = ifnull($(this).find("firstName").text());
            document.getElementById("DOB").value = ifnull(TimeStampToDate($(this).find("dateOfBirth").text()));
            document.getElementById("Gender").value = ifnull($(this).find("gender").text());
            document.getElementById("personProfileBut").innerHTML = "Update";
        })
        //document.getElementById("loader_comp"). setAttribute("class","loader-sm overlay_center");
        hideLoader();
        console.log("<<---prepPersonProfileForm");
    }
    ;


    function personProfileSaveUpdateHandler() {
        $("#personform").submit(function (event) {
            event.preventDefault();
            console.log("clicked " + $("#personProfileBut").text());
            var Person = JSON.stringify({
                name: $('#Name').val(),
                firstName: $('#FirstName').val(),
                lastName: $('#LastName').val(),
                lastUpdatePersonID: credentialPersonID,
                dateOfBirth: new Date($('#DOB').val()),
                gender: $('#Gender').val()
            });
            console.log("This is the text of the button: " + $("#personProfileBut").text());
            if ($("#personProfileBut").text() === "Save") {
                saveProfile(Person, function (data) {
                    showAlert("New Record Successfully Created. Record # " + $(personProfile).find("idPerson").text());
                    prepActivePerson(personProfile);
                    prepPersonProfileForm(personProfile);
                });
            }
            if ($("#personProfileBut").text() === "Update") {
                console.log("updating person profile # " + working_person_id);

                updateProfile(Person, function () {
                    showAlert("Personal Profile Successfully Updated");
                    getPersonalProfile(working_person_id);
                    prepPersonProfileForm(data);
                })


            }
        });
    }
    ;
//----------------------------------------------------- Job Qualification


    $("#jobqualification").on("click", function (e) {
        if (working_person_id == null || working_person_id == "") {
            showAlert("Search for a record first to associate Job Qualification or create new profile.");
        } else {
            getJobQualificationByPersonID(function () {
                showQualificationForm();
            });
        }
    });
    function showQualificationForm() {
        console.log("showQualificationForm() called.....");
        $("#panel").remove();
        $("#section1").load("htmlcomponents/jobqualification.html", function () {
            if (jobQualification == null) {
                console.log("Job Qualification null");
                document.getElementById("jobQualificationBut").innerHTML = "Save";
                jobQualificationSaveUpdateHandler();
            } else {
                console.log("Lookup for existing job qualification " + working_person_id);
                prepJobQualificationForm(jobQualification);
                jobQualificationSaveUpdateHandler();
            }
        });
    }
    ;
    function prepJobQualificationForm(jobQualificationXMLdata) {
        console.log("prepJobQualificationForm--->>>");
        $(jobQualificationXMLdata).find("jobQualification").each(function () {
            //alert($(this).find("industryLevelIdindustryLevel").text());
            document.getElementById("jobTitle").value = ifnull($(this).find("jobTitle").text());
            document.getElementById("company").value = ifnull($(this).find("company").text());
            document.getElementById("yrsOfExperience").value = ifnull($(this).find("yrsOfExperience").text());
            document.getElementById("currentSalary").value = ifnull($(this).find("currentSalary").text());
            document.getElementById("targetSalary").value = ifnull($(this).find("targetSalary").text());
            document.getElementById("targetPosition").value = ifnull($(this).find("targetPosition").text());
            document.getElementById("skillsCategorymo").value = ifnull($(this).find("skillsCategorymo").text());
            document.getElementById("joblevelmo").value = ifnull($(this).find("joblevelmo").text());
            document.getElementById("priority").value = ifnull($(this).find("priority").text());
            document.getElementById("industriesIdindustries").value = ifnull($(this).find("industriesIdindustries").find("idindustries").text());
            document.getElementById("industryLevelIdindustryLevel").value = ifnull($(this).find("industryLevelIdindustryLevel").find("idindustryLevel").text());
            document.getElementById("payrateIdpayrate").value = ifnull($(this).find("payrateIdpayrate").find("idpayrate").text());
            //document.getElementById("qualificationSummary").value = ifnull($(this).find("qualificationSummary").text());
            document.getElementById("skills").value = ifnull($(this).find("skills").text());
            //document.getElementById("searchText").value = ifnull($(this).find("searchText").text());
        });
        document.getElementById("jobQualificationBut").innerHTML = "Update";
        console.log("<<---prepJobQualificationForm");
    }
    ;

    function jobQualificationSaveUpdateHandler() {
        $("#jobqualificationform").submit(function (event) {
            event.preventDefault();
            console.log("clicked jobQualificationSaveUpdateHandler :" + $("#jobQualificationBut").text());
            var JobQualificationData = JSON.stringify({
                idJobQualification: working_jobqualification_id,
                jobTitle: $('#jobTitle').val(),
                company: $('#company').val(),
                skillsCategorymo: $('#skillsCategorymo').val(),
                joblevelmo: $('#joblevelmo').val(),
                qualificationSummary: $('#qualificationSummary').val(),
                yrsOfExperience: $('#yrsOfExperience').val(),
                currentSalary: $('#currentSalary').val(),
                targetSalary: $('#targetSalary').val(),
                targetPosition: $('#targetPosition').val(),
                priority: $('#priority').val(),
                skills: $('#skills').val(),
                searchText: $('#searchText').val(),
                industriesIdindustries: {idindustries: document.getElementById("industriesIdindustries").value},
                industryLevelIdindustryLevel: {idindustryLevel: $('#industryLevelIdindustryLevel').val()},
                payrateIdpayrate: {idpayrate: $('#payrateIdpayrate').val()}
            });
            console.log("This is the text of the button: " + JobQualificationData.toString());
            if ($("#jobQualificationBut").text() === "Save") {
                console.log("saving new record person profile.");
                saveJobQualification(JobQualificationData, function () {
                    showAlert("Job Qualification Successfully Created. - " + $(jobQualification).find("jobTitle").text());
                    prepJobQualificationForm(jobQualification);
                })
            }
            if ($("#jobQualificationBut").text() === "Update") {
                console.log("updating job qualification with ID # " + working_jobqualification_id);
                updateJobQualification(JobQualificationData, function () {
                    showAlert("Successfully updated Job Qualification.");
                })


            }
        });
    }

//-------------------------contacts

    $("#contact").on("click", function (e) {
        if (working_person_id == null || working_person_id == "") {
            showAlert("Search for a record first to associate this contact or create new profile.");
        } else {
            getContactByPersonID(function () {
                showContactForm();
            });
        }
    });
    function showContactForm() {
        console.log("showContactForm() called.....");
        $("#panel").remove();
        $("#section1").load("htmlcomponents/contactInfo.html", function () {
            if (contactInfo == null) {
                console.log("contactInfo null");
                document.getElementById("contactBut").innerHTML = "Save";
                contactSaveUpdateHandler();
            } else {
                console.log("Lookup for existing conract " + working_person_id);
                prepContactForm(contactInfo);
                contactSaveUpdateHandler();
            }
        });
    }

    function prepContactForm(contactInfoXMLdata) {
        console.log("prepContactForm--->>>");
        $(contactInfoXMLdata).find("contact").each(function () {
            //showAlert($(this).find("contactNum").text());
            document.getElementById("contactNum").value = ifnull($(this).find("contactNum").text());
            document.getElementById("cellphoneNum").value = ifnull($(this).find("cellphoneNum").text());
            document.getElementById("email").value = ifnull($(this).find("email").text());
            document.getElementById("address").value = ifnull($(this).find("address").text());
            document.getElementById("city").value = ifnull($(this).find("city").text());
            document.getElementById("country").value = ifnull($(this).find("country").text());
        });
        document.getElementById("contactBut").innerHTML = "Update";
        console.log("<<---prepContactForm");
    }

    function contactSaveUpdateHandler() {
        $("#contactForm").submit(function (event) {
            event.preventDefault();
            console.log("clicked contactSaveUpdateHandler :" + $("#contactBut").text());
            var ContactData = JSON.stringify({
                idcontact: working_contact_id,
                telNum1: $('#jobTitle').val(),
                contactNum: $('#contactNum').val(),
                cellphoneNum: $('#cellphoneNum').val(),
                email: $('#email').val(),
                address: $('#address').val(),
                city: $('#city').val(),
                country: $('#country').val(),
            });
            console.log("This is the text of the button111: " + ContactData.toString());
            if ($("#contactBut").text() == "Save") {
                console.log("saving new contact.");
                saveContact(ContactData, function () {
                    getContactByPersonID(function () {
                        showAlert("Contact Successfully Created. - " + $(contactInfo).find("email").text());
                        prepContactForm(contactInfo);
                    })
                })
            }
            if ($("#contactBut").text() == "Update") {
                console.log("updating person profile # " + working_person_id);
                //JobQualificationData["jobQualificationPK"] = rea$(jobQualification).find("jobQualification").find("idJobQualification").text();
                updateContact(ContactData, function () {
                    showAlert("You have just successfully updated the contact.");
                })
            }
        });
    }

//------------------------- activities

    $("#activities-but").on("click", function (e) {
        if (working_person_id == null || working_person_id == "") {
            //showAlert("Search for a record first to view activities.");
        } else {
            $("#panel_activity").remove();
            $("#rem_container").load("htmlcomponents/activities.html", function () {
                $.getScript("js/component/activities.js");
                activatePill("activity_pill");
            });
        }
    });

    function getRemarksFormData() {
        var remarksData = JSON.stringify({
            remarks: credentialID,
            nsbactivitiesidSourcingActivities: {idSourcingActivities: '1'},
        });
        return remarksData;
    }

    function addRemarks(callback) {
        console.log("clicked activities save handler");
        $("#activityform").submit(function (event) {
            event.preventDefault();
            console.log("clicked contactSaveUpdateHandler :" + $("#sourcingBut").text());
            //activityData = getActivityFormData();
            console.log("saving new contact.");
            $.ajax({
                type: 'POST',
                url: add_remark_url,
                contentType: 'application/json',
                data: getRemarksFormData(),
                success: function (data) {
                    getActivities(function () {
                        showActivityForm();
                    });

                    if (callback && typeof (callback) === "function") {
                        //do something here from your call back function
                        console.log("Calling the callback inside the function getActivities...")
                        callback(data);
                    }
                    ;
                }
            });
        })
    }


//-------------------------- remarks

    $("#remarks-but").on("click", function (e) {
        if (working_person_id == null || working_person_id == "") {
            //showAlert("Search for a record first to view activities.");
        } else {
            $("#remarks-panel").remove();
            $("#rem_container").load("htmlcomponents/remarks.html", function () {
                $.getScript("js/component/remarks.js");
            });
        }
    });


});
//----------------------------------------------------------------------document.ready ends here


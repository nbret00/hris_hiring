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


    $("#newCandidateForm").submit(function (event) {
        event.preventDefault();
        
        if (getNamesFormDataSize() > 0) {
            var profiledata = JSON.stringify({
                firstName: $("#FirstName").val(),
                lastName: $("#LastName").val(),
                name: $("#name").val()
            });
            saveProfile(profiledata, function(){
                console.log("saveProfile done")
            })
        }
        if (getContactsFormDataSize() > 0) {
            console.log("contacts present");
        }

    })


})
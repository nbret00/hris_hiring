/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    console.log("loaded generalInformation"+searchNamesForm_fname);
    //showAlert("This is my message!!"+searchNamesForm_fname);
    $("#FirstName").val(searchNamesForm_fname);
   
    
    function getNamesFormData(){
        
    }
    
    function getNamesFormDataSize(){
        var str = $("#FirstName").val()+$("#LastName").val()+$("#name").val();
        return str.length;
    }
    function getContactsFormDataSize(){
        var str = $("#mobilenum").val()+$("#email").val();
        return str.length;
    }    
    
    $("#newCandidateForm").submit(function (event) {
        event.preventDefault();
        console.log("Submit button!"+getNamesFormDataSize());
        
        if (getNamesFormDataSize()>0){
            console.log("names present");
            
        }
        if (getContactsFormDataSize()>0){
            console.log("contacts present");
        }
        
    })
    
    
})
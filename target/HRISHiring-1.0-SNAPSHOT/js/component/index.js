/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {



console.log("document.URL : "+document.URL);
console.log("document.location.href : "+document.location.href);
console.log("document.location.origin : "+document.location.origin);
console.log("document.location.hostname : "+document.location.hostname);
console.log("document.location.host : "+document.location.host);
console.log("document.location.pathname : "+document.location.pathname);

    var login_url = "http://localhost:8080/hris_hiring/webresources/hrisaccount/validate";
    var home_url = "http://localhost:8080/hris_hiring/home.html";
    var login_page_url = "http://localhost:8080/hris_hiring/index.html?noaccount";
    //init
    //TODO: 1. should check if the user was authenticated therefore load the home instead
    
    //alert("param"+location.search);
    if (location.search != ''){
        if (location.search=="?noaccount"){
            document.getElementById("loginalert").hidden = false;
        }
        
        if (location.search=="?nologin"){
            document.getElementById("loginalert").hidden = false;
            $("#alertmsg").replaceWith('<div id="alertmsg"><strong>Info!</strong> Please login again.</div>');
        }
        
        if (location.search=="?logoutok"){
            document.getElementById("loginalert").hidden = false;
            $("#alertmsg").replaceWith('<div id="alertmsg"><strong>Info!</strong> Logout Successfully.</div>');
        }        
    }
    
    
    $("#customlogin").submit(function (event) {

        console.log("clicked");
        event.preventDefault();
        // Stop form from submitting normally
        var data = {
            Username: $('#Username').val(),
            Password: $('#Password').val(),
        }
        
        var dt = $("#customlogin");
        console.log(dt.toString());
        //var fd = new FormData(dt);

        //user = $("#User").val();
        //pass = $('#pass').val();

        console.log("This is serialized" + data);
        
        $.ajax({
            type: 'POST',
            url: login_url,
            contentType: 'application/x-www-form-urlencoded',
            data: data,
            success: function (data) {
                if ("success" == data){
                    console.log("success");
                    window.location.href = home_url;
                }else{
                    window.location.href = login_page_url;
                    console.log("not authenticated");
                }
            },
            error: function (jqXHR, status) {
                alert("Status:1 " + status);

            }
        });
        
    });
    
})
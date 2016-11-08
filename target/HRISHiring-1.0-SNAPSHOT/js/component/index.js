/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

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
            //url: 'http://lowcost-env.f6cahdjuip.ap-southeast-1.elasticbeanstalk.com/webresources/com.nino.app.hrishiring.company/',
            //url: 'http://localhost:8080/HRISHiring/webresources/com.nino.app.hrishiring.hrisaccount/add',
            url: 'http://localhost:8080/HRISHiring/webresources/hrisaccount/validate',
            contentType: 'application/x-www-form-urlencoded',
            //dataType: 'jsonp',
            data: data,
            //crossDomain: true,
            //"Access-Control-Allow-Origin: " : "*",
            success: function (data) {
                if ("success" == data){
                    console.log("success");
                    window.location.href = "http://localhost:8080/HRISHiring/home.html";
                }else{
                    window.location.href = "http://localhost:8080/HRISHiring/index.html?noaccount";
                    console.log("not authenticated");
                }
            },
            error: function (jqXHR, status) {
                alert("Status:1 " + status);

            }
        });
        
        



    });
    
})
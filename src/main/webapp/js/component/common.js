
function showAlert(msg) {
    $("#alert-msg").html("<p>" + msg + "</p>");
    $("#myModalalert").modal();
}

function lookupSelectValue(url, selinput, objname, opt_id_dom, name_dom, active_id, callback) {

    $.ajax({
        type: 'GET',
        url: url,
        success: function (data) {

            if (selinput != null) {

                $(data).find(objname).each(function () {
                    var opt_text = $(this).find(name_dom).text();
                    var opt_id = $(this).find(opt_id_dom).text();
                    if (active_id == opt_id) {
                        $(selinput).append("<option selected='selected' value='" + opt_id + "'>" + opt_text + "</option>");
                    } else {
                        $(selinput).append("<option value='" + opt_id + "'>" + opt_text + "</option>");
                    }
                });
            }
            if (callback && typeof (callback) === "function") {
                //do something here from your call back function
                //console.log("Calling the callback inside the function getActivities...")
                callback(data);
            }
            ;
        },
        error: function (jqXHR, status) {
            showAlert("Application Error encountered in getFramework: " + status);
        }
    });
}


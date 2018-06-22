function errorMessage(id, msg) {
	$(id).html(`<p class="error">${msg}</p>`);
}


let milogin = function () {
    let _isValid = document.getElementById('milogin').checkValidity();
    let outcome = { "isValid": _isValid, };

    $('form .error').remove();

    //EMAIL
    $('#email').each(function () {
        let email = this.validity;

        if (email.valid == false) {
            if (email.patternMismatch) {
            	errorMessage('#mail_error', 'Formato incorrecto de email');
            }
            if (email.valueMissing) {
            	errorMessage('#mail_error', 'No puede dejar email en blanco');
            }
        }
    });

    //PASSWORD
    $('#password').each(function () {
        let passValidity = this.validity;
        if (passValidity.valueMissing) {
        	errorMessage('#pass_error', 'No puede dejar password en blanco');
        }
    });

    return outcome;
}



if(document.getElementById("submitBtn"))
	document.querySelector("#submitBtn").onclick=function(evnt){
		evnt.preventDefault();
		if(milogin().isValid) {
			document.forms[0].submit();
		}
}

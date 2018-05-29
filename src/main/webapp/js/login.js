let milogin = function () {
    let _isValid = document.getElementById('milogin').checkValidity();
    let outcome = {
        "isValid": _isValid,
        "values": {}
    };

    $('form .error').remove();

    //EMAIL
    $('#email').each(function () {
        let email = this.validity;

        if (email.valid) {
            outcome.values["email"] = this.value;
        } else {
            if (email.patternMismatch) {
                $('#mail_error').html('<p class="error">Email incorrecto!</p>')
            }
            if (email.valueMissing) {
                $('#mail_error').html('<p class="error">Rellene todos los campos!</p>')
            }
        }
    });

    //PASSWORD
    $('#password').each(function () {
        let passValidity = this.validity;
        console.log(passValidity);
        
        if (passValidity.valid) {
            outcome.values["password"] = this.value;
        }
        
        if (passValidity.valueMissing) {
            $('#pass_error').html('<p class="error">Rellene todos los campos!</p>')
        }
       
    });

    return outcome;
}

if(document.getElementById("submitBtn")) document.querySelector("#submitBtn").onclick=function(evnt){
	evnt.preventDefault();
	
	let validEmail=document.querySelector("input[name=email]").checkValidity();
	let validPass=document.querySelector("input[name=password]").checkValidity();
	
	if(!validEmail || !validPass) {
		document.querySelector("#showerror").innerHTML="Datos mal formados";
	}else{
		document.forms[0].submit();
	}
}

//$('#submitBtn').click(function (event) {
//    event.preventDefault();
//    let objeto = milogin();
//    console.log('dtaos a enviar al servlet', objeto, 'formData', formData);
//    if (objeto.isValid ) {
//        $.ajax({
//            //url: 'http://www.mocky.io/v2/5ae1741c2d000057009d7c06',
//            url: 'http://localhost:9090/WorkerApp2/login',
//            method: 'POST',
//            data: formData
//           
//        }).done(function (datoRecibido) {
//        	console.log(datoRecibido);
//            if (datoRecibido.result) {
//                sessionStorage.setItem('usuarioAutenticado', JSON.stringify(datoRecibido.usuario));
//                location.href = './login_confirm.jsp';
//            } else {
//                $('#pass_error').html('<p class="error">usuario y Contraseña errónea!</p>')
//            }
//        });
//    }
//});

function setErrorMsg(errorMsgID, errorMsg) {
	//console.log(errorMsgID, errorMsg);
	document.getElementById( errorMsgID ).innerHTML = errorMsg;
}



function valueMissingError(inputId, errorMsgID, errorMsg) {
	$(inputId).each(function () {
		hasError = this.validity.valueMissing;
		if (hasError) {
			setErrorMsg(errorMsgID, errorMsg);
		}
	});
	//console.log(hasError, 'ERROR', errorMsg);
	return hasError ? 1 : 0;
}



function formatError(inputId, errorMsgID, errorMsg, regex) {
	let input = $(inputId).val();
	let hasError = ! new RegExp(regex).test(input);
	if (hasError) {
		setErrorMsg(errorMsgID, errorMsg);
	}
	//console.log(hasError, 'ERROR', errorMsg);
	return hasError ? 1 : 0;
}



function passwordMismatchError(inputId1, inputId2, errorMsgID, errorMsg) {
	let hasError = ($( inputId1 ).val() != $( inputId2 ).val());
	if (hasError) {
		setErrorMsg(errorMsgID, errorMsg);
	}
	//console.log(hasError, 'ERROR', errorMsg);
	return hasError ? 1 : 0;
}



let numeroErroresFormulario = function () {
	const EMAIL_REGEX = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	const PASSWORD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,16})/;
	const NO_NOMBRE = 'Introduce tu nombre';
	const NO_APELLIDOS = 'Introduce tus apellidos';
	const NO_EMAIL = 'Introduce un email';
	const WRONG_EMAIL = 'Introduce un email Válido';
	const WRONG_PASSWORD = 'Formato del password:<br>Entre 8 y 16 carácteres<br>Minúsculas<br>Mayúsculas<br>Números<br>Símbolos admitidos: !@#$%^&*';
	const NO_PASSWORD = 'Introduce un password';
	const PASSWORD_MISMATCH = 'Los passwords no coinciden';
	const NO_REPASSWORD = 'Confirma tu password';
	document.querySelectorAll('form .error').forEach(function(element){element.innerHTML = "";});
	let numErrors = 0;
	// Para determinar que mensaje prevalece en un campro con multiples verificaciones
	// el orden de los las verificaciones es importante
    numErrors += valueMissingError('#name', 'nombre_msg', NO_NOMBRE);
    numErrors += valueMissingError('#lastname', 'apellidos_msg', NO_APELLIDOS);
    numErrors += formatError('#email', 'email_msg', WRONG_EMAIL, EMAIL_REGEX);
    numErrors += valueMissingError('#email', 'email_msg', NO_EMAIL);
    numErrors += formatError('#pass', 'pass_msg', WRONG_PASSWORD, PASSWORD_REGEX);
    numErrors += formatError('#repass', 'repass_msg', WRONG_PASSWORD, PASSWORD_REGEX);
    numErrors += valueMissingError('#pass', 'pass_msg', NO_PASSWORD);
    numErrors += passwordMismatchError('#pass', '#repass', 'repass_msg', PASSWORD_MISMATCH);
    numErrors += valueMissingError('#repass', 'repass_msg', NO_REPASSWORD);
    //console.log('numErrors', numErrors);
    return numErrors;
}



function validarFormularioNuevoUsuario(evnt) {
	evnt.preventDefault();
	//console.clear();
	if (numeroErroresFormulario() === 0) {
		//console.log('submit');
		document.getElementById('registroForm').submit();
	}
}



(function (){
	document.getElementById('register').onclick = validarFormularioNuevoUsuario;
})();
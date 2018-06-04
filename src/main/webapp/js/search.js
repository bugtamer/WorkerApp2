function buscar(event) {
	event.preventDefault();
	console.log(`on submit() >>> $('#icon_prefix2').val()='${$('#icon_prefix2').val()}'`);
	const SEARCH_FORM = document.getElementById('search_form');
	if ($('#icon_prefix2').val() == "") {
        $('#sugerencias').html('<p class="error">El campo de búsqueda no puede estar vacío</p>');
    } else {
    	SEARCH_FORM.submit();
    }
}



(function () {
	document.getElementById('search_button').onclick = function (evnt) {
		buscar(evnt);
	};
	
	
	document.getElementById('icon_prefix2').onkeypress = function (e) {
	    const ENTER_KEY_CODE = 13;
	    if (e.keyCode == ENTER_KEY_CODE) {
			buscar(e);
		}
	};
})();
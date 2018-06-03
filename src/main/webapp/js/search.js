(function () {
	const SEARCH_FORM = document.getElementById('search_form');
	SEARCH_FORM.onsubmit = function (event) {
		event.preventDefault();
	    if ($('#icon_prefix2').val() == "") {
	        $('#sugerencias').html('<p class="error">El campo de búsqueda no puede estar vacío</p>');
	    } else {
	    	SEARCH_FORM.submit();
	    }
	}
	
	
	document.getElementById('search_button').onclick = function (evnt) {
	    SEARCH_FORM.submit();
	};
	
	
	document.getElementById('icon_prefix2').onkeypress = function (e) {
	    const ENTER_KEY_CODE = 13;
	    if (e.keyCode == ENTER_KEY_CODE) {
		    SEARCH_FORM.submit();
	    }
	};
})();
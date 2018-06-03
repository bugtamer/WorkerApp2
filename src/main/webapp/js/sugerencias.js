function eventoAceptarSugerencia() {
    $('#sugerencias > ul > li.teSuguiero').click(function(event){
    	let sugerencia = this.innerText;
    	let id = this.getAttribute('data-id');
        document.getElementById('icon_prefix2').value = sugerencia;
        this.remove();
        document.getElementById("icon_prefix2").focus();
    });
};



function generarListaSugerencias(arraySugerencias) {
	let listaSugerencias = '<ul>';
    for (let index = 0; index < arraySugerencias.length; index++) {
    	listaSugerencias += `<li class="teSuguiero" data-id="${index}">${arraySugerencias[index]}</li>`;
    }
    listaSugerencias += '</ul>';
	return listaSugerencias;
}



(function (){
	let sugerenciasElement = document.getElementById('sugerencias');
	document.getElementById('icon_prefix2').addEventListener('keyup', function (event) {
		const MIN_PRESSED_KEYS = 2;
	    if ((this.value)  &&  (this.value.length >= MIN_PRESSED_KEYS)) {
    		$.ajax({
    			url:     `./sugerencias?terminoDeBusqueda=${this.value}`,
    			accepts: "application/json; charset=utf-8",
    		}).done(function(resultadosServidor) {
    	        sugerenciasElement.innerHTML = generarListaSugerencias(resultadosServidor);
    	        eventoAceptarSugerencia();
    		}).fail(function (error) {
    			console.log('sugerencias.js: connection error', error);
    		});
    	} else {
    		sugerenciasElement.innerHTML = "";
    	}
	});})();

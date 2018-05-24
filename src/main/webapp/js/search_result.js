(function () {
    let conexionBBDD = {
        url: 'http://www.mocky.io/v2/5ae0e3113200002a00510d5b',
        method: 'POST'
    }


    $.ajax(conexionBBDD).done(function (respuestaServidor) {
        let profesionales = respuestaServidor.resultados;
        let resultados = '';
        for (i = 0; i < profesionales.length; i++) {
            resultados += `
            <li class="collection-item avatar itemResultado">
                <div class="listaClickable">
                    <img src="${profesionales[i].imagen}" alt="${profesionales[i].nombre}, ${profesionales[i].actividad}" class="circle">
                    <span class="title">${profesionales[i].nombre}</span>
                    <p>${profesionales[i].actividad}</p>
                    <a href="${profesionales[i].detalle}" class="secondary-content">
                        <h5>${profesionales[i].distancia}Km</h5>
                        <i class="${profesionales[i].valoracion > 4 ? 'material-icons' : 'material-icons no_active'}">grade</i>
                        <i class="${profesionales[i].valoracion > 3 ? 'material-icons' : 'material-icons no_active'}">grade</i>
                        <i class="${profesionales[i].valoracion > 2 ? 'material-icons' : 'material-icons no_active'}">grade</i>
                        <i class="${profesionales[i].valoracion > 1 ? 'material-icons' : 'material-icons no_active'}">grade</i>
                        <i class="${profesionales[i].valoracion > 0 ? 'material-icons' : 'material-icons no_active'}">grade</i>
                    </a>
                <div>
            </li>`;
        }

        $('#listaResultados').html(resultados);
        let mensajeResultados = `Se ${profesionales.length > 1 ? 'han' : 'ha'} encontrado 
            ${profesionales.length} ${profesionales.length > 1 ? 'resultados': 'resultado'} de
            <br><span>"${respuestaServidor.busqueda}"</span>`;
        $('#resumenResultado').html(mensajeResultados);
        
        // una vez actualizado el DOM
        $('.itemResultado').each(function (i){
            this.addEventListener('click', function(){
                location = profesionales[i].detalle;
            });
        });
    }).fail(function (error) {
        alert('Hubo un error con la petición. Comprueba tu conexión.');
    });
}());
(function(){
    $.ajax('http://www.mocky.io/v2/5ae0f2f03200006d00510da2')
    .done(function(respuestaServidor){
        document.getElementById('ticket').innerText = `Ticket nº (${respuestaServidor.ticketNum})`;
        document.getElementById('fecha').innerText = respuestaServidor.fecha;
        document.getElementById('vencimiento').innerText = respuestaServidor.vencimiento;
        
        let subtotal = 0;
        let htmlConceptos = '<table>';
        for (let i = 0; i < respuestaServidor.conceptos.length; i++) {
            let item = respuestaServidor.conceptos[i];
            htmlConceptos += `<tr><td>${item.cantidad}</td><td>${item.descripcion}</td><td>${item.precio} €</td></tr>`;
            let cantidad = item.cantidad;
            let precio   = parseFloat(item.precio);
            subtotal += cantidad * precio;
        }
        htmlConceptos += '</table>';
        const IVA = .21;
        let subtotalIVA = IVA * subtotal;
        let total = subtotal + subtotalIVA;

        document.getElementById('conceptos').innerHTML = `${htmlConceptos}`;
        document.getElementById('subtotal').innerText = `${subtotal}`;
        document.getElementById('iva').innerText = `${IVA * 100}`;
        document.getElementById('subtotalIVA').innerText = `${subtotalIVA}`;
        document.getElementById('total').innerText = `${total}`;
    })
    .fail(function(){
        alert('Hubo un error con la petición. Comprueba tu conexión.');
    });
})();
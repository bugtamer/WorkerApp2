// una vez actualizado el DOM
$('.itemResultado').each(function (i){
    this.addEventListener('click', function(){
        location = profesionales[i].detalle;
    });
});
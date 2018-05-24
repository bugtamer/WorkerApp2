function aceptarSugerencia() {
    $('.teSuguiero').each(function(){
        addEventListener('click', function(event){
            document.getElementById('icon_prefix2').value = event.srcElement.innerText;
            event.srcElement.remove();
        });
    });
};
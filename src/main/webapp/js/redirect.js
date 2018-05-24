var count = 2;
setInterval(function(){
    count--;
    document.getElementById('timecount');
    if (count == 0) {
        url = sessionStorage.getItem('ultimaPaginaVisitada');
        window.location = url ? url : './index.html'; // por si el usuario visita directamente login.html sin otra página previa
    }
},1000);
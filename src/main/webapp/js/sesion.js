// IMPORTANTE
// Este script DEBE cargarse ANTES que los scripts específicos de página
// para tener acceso a la información de sesion del USUARIO REGISTRADO
// y DESPUÉS de los genéricos como de Materialize o jQuery

function getUsuarioAutenticado() {
    // TODO Si hay token: confirmar al servidor que no ha caducado,
    //      si lo estubiera eliminar usuarioAutenticado de la session
    //      y devolver null
    return JSON.parse( sessionStorage.getItem('usuarioAutenticado') ); // null si no existe
}

function getSessionToken() {
    let usuarioAutenticado = getUsuarioAutenticado();
    return usuarioAutenticado ? usuarioAutenticado.token : null;
}

function getSessionAvatar() {
    let usuarioAutenticado = getUsuarioAutenticado();
    return usuarioAutenticado ? usuarioAutenticado.avatar : './imgs/no_user.png';
}

function getSessionNombre() {
    let usuarioAutenticado = getUsuarioAutenticado();
    return usuarioAutenticado ? usuarioAutenticado.nombre : 'Visitante';
}



function conmutarLogInOut() {
    let log;
    if ( getSessionToken() ) {
        log = document.getElementsByClassName('conmutadorLog');
        for(i=0;   i < log.length;   i++) {
            log[i].href      = './index.html';
            log[i].innerText = 'Logout';
            log[i].addEventListener('click', function(){
                sessionStorage.removeItem('usuarioAutenticado');
            });
        }
    } else {
        log = document.getElementsByClassName('conmutadorLog');
        for (let i=0;   i < log.length;   i++) {
            log[i].href      = './login.html';
            log[i].innerText = 'Login';
        }
    }
}



function conmutarAvatar() {
    let avatar = document.querySelector('ul.right.user_header > li > a > img');
    avatar.src = getSessionAvatar();
}



function paginaActual() {
    let url = (location.href) . split('/'); // obtiene array partiendo el string
    return url [ url.length - 1] ;
}



(function(){
    conmutarAvatar();
    conmutarLogInOut();

    switch ( paginaActual() ) {
        case 'login.html':
        case 'registro.html':
            // entro directamente sin otra visita previa
            if ( ! sessionStorage.getItem('ultimaPaginaVisitada') ) { // no hay ultima visita
                sessionStorage.setItem('ultimaPaginaVisitada', '/index.html');
            }
            break;
        
        default:
            sessionStorage.setItem('ultimaPaginaVisitada', window.location.href);
    }

    // prohibir el acceso a las areas privadas sin autenticarse
    if ( ! getSessionToken() ) {
        switch ( paginaActual() ) {
            case 'ficha.html':
            case 'ticket.html':
            case 'chat.html':
                window.location.href = './login.html';
        }
    }
})();
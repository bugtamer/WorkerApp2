var chatName = 'chat';

// muestra un mensaje en la interfaz
function mostrar(mensaje) {
    let listItem = '';
    switch(mensaje.autor) {
        case 'self':
        case 'other':
            let imgURL = mensaje.imagen;
            let imgName;
            if(imgURL) {
                imgName = imgURL.split('/');
                imgName = imgName[imgName.length - 1];
                imgName = imgName.substring(0, imgName.length - 4);
            }
            listItem = `
            <li class="${(mensaje.autor === 'self') ? 'self' : 'other'}">
                <div class="msg">
                    <div class="user">${(mensaje.autor === 'self') ? '<span class="range admin">Admin</span>' : mensaje.nombre}</div>
                    ${mensaje.imagen ? `<img src="${imgURL}" alt="${imgName}" draggable="false" />` : ''}
                    <p>${mensaje.mensaje ? mensaje.mensaje : ''}</p>
                    <time>${mensaje.hora}</time>
                </div>
            </li>`;
            break;
        
        case 'notification':
            listItem = `<p class="notification">${mensaje.mensaje}<time>${mensaje.hora}</time></p>` // XXX <li>...</li>
            break;
        
        default:
            console.log(`ERROR (chat) tipo de autor desconocido='${mensaje.autor}'`);
    }
    $('#chat').append(listItem);
    // mover el scroll hasta la parte inferior
    let altura = $(document).height();
    $('html, body').animate({ scrollTop: altura }, 10);
}


// persistencia de las conversaciones
function almacenar(mensaje) {
    let chat = localStorage.getItem('chat');
    chat = chat ? JSON.parse(chat) : [];
    chat.push(mensaje);
    localStorage.setItem(chatName, JSON.stringify(chat));
}



// source: https://www.html5rocks.com/en/tutorials/file/dndfiles/
// source: https://www.nczonline.net/blog/2012/05/08/working-with-files-in-javascript-part-1/
function getAdjunto(event) {
    let URLs = null;
    if (!(window.File && window.FileReader && window.FileList && window.Blob)) {
        alert('Tu dispositivo no tiene completo soporte para trabajar con ficheros.');
    } else {
        // TODO procesar archivos, URLs, etc.
        // TODO subir archivos, ...
    }
    return URLs;
}



// evento lanzado por el usuario al hacer click
// event opcional
function enviar(socket, event) {
    let adjunto = event ? getAdjunto(event) : null;
    let usuarioId = document.getElementById('usuarioId').value;
    let manitasId = document.getElementById('manitasId').value;
    let texto     = document.getElementById('texto').value;
    if (texto || adjunto) {
        document.getElementById('texto').value = '';
        let ahora   = new Date();
        let objetoMensaje = {
           	usuarioId: usuarioId,
           	manitasId: manitasId,
        	autor:     "self",
            nombre:    getSessionNombre(),
            mensaje:   texto,
            imagen:    adjunto,
            hora:     `${ahora.getHours()}:${ahora.getMinutes()}:${ahora.getSeconds()}`
        };
        almacenar(objetoMensaje);
        mostrar(objetoMensaje);
        socket.send( JSON.stringify(objetoMensaje) );
    } else {
        document.getElementById('texto').placeholder ='No has escrito o adjuntado nada para enviar';
        $('#texto').addClass('advertencia');
    }
    $('#texto').focusin( function(){$('#texto').removeClass('advertencia')} );
}



function getSessionNombre() {
	return document.getElementById('usuarioNombre').value; // antes se obtenia de session.js
}



// evento lanzado por WebSocket cuando recibe un mensaje de su interlocutor
function recibir(event) {
    console.log("recibir", event);
    
    let mensaje = JSON.parse(event.data);
    mensaje.autor = mensaje.autor === "self" ? "other" : mensaje.autor;
    almacenar(mensaje);
    mostrar( mensaje );
}



// https://stackoverflow.com/questions/19491336/get-url-parameter-jquery-or-how-to-get-query-string-values-in-js#
function getUrlParameter(sParam) {
    let sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};



// función anónima autoinvocada para iniciar el chat (si es posible)
(function(){
    if (typeof(WebSocket) === undefined) {
        alert('Su dispositivo no soporta la tecnología WebSocket necesaria para hacer funcionar el chat.');
    } else {
        // establecer WebSocket
        const URL = `ws://${window.location.host}/WorkerApp2/mensaje`;
        let chatSocket = new WebSocket(URL); // PROTOCOLO: atributo opcional
        
        // captura de eventos
        chatSocket.onmessage = function(event) { recibir(event) };
        chatSocket.onerror   = function(event) { alert('La comunicación con el servidor falló.') };
        $('#btn_enviar').click( function(event){
            event.preventDefault();
            enviar(chatSocket);
        });
        document.getElementById('icono_btn_adjunto').onclick = function() { // icono visible
            let btnAdjunto = document.getElementById("btn_adjunto");        // botón oculto
            btnAdjunto.click();
            btnAdjunto.addEventListener('change', function (event) { // ¿hay nuevos archivos?
                enviar(chatSocket, event);
            }, false);
        };
        
        let uid = getUrlParameter('uid');
        let manitasId = getUrlParameter('manitasId');
        chatName = `chat-u${uid}p${manitasId}`;

        // carga / inicializacion conversaciones chat o
        let chat = localStorage.getItem(chatName);
        if (chat) {
            chat = JSON.parse(chat);
            for (let i = 0;   i < chat.length;   i++) {
                mostrar( chat[i] );
            }
        } else { // carga la conversación desde la BBDD
            $.ajax(`./getMensajes?uid=${uid}&manitasId=${manitasId}`).done(function(respuestaServidor){
                for (let i = 0;   i < respuestaServidor.length;   i++) {
                    mostrar   ( respuestaServidor[i] );
                    almacenar ( respuestaServidor[i] );
                }
            });
        }
    }
    window.onload = function() {
        document.getElementById('texto').focus();
    }
})();
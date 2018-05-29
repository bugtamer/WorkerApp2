package com.worker.controller;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.worker.db.DDBB;
import com.worker.models.Mensaje;
import com.worker.models.Usuario;


/**
 * source: http://pance.mk/index.php/websockets-in-java/
 */
@ServerEndpoint("/mensaje")
public class MensajeServerEndpoint {
	
	
	@OnOpen
	public void onOpen(Session session) throws IOException {
		System.out.println("onOpen()");
		session.getBasicRemote().sendText("Connected!");
	}
	
	
	@OnMessage
	public void onMessage(String message) {
		Mensaje nuevoMensaje = parseMensaje(message);
		DDBB.getInstance().addMensaje(nuevoMensaje);
	}
	
	
	@OnClose
	public void onClose(Session session, CloseReason reason) {
		System.out.println("onClose()");
	}
	
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println("onError()");
	}
	
	
	
	// DETALLES DE IMPLEMENTACION DE MÁS BAJO NIVEL
	
	private Mensaje parseMensaje(String mensajeChatJSON) {
		System.out.println("onMessage()=" + mensajeChatJSON);
		DDBB db = DDBB.getInstance();
		Usuario emisor    = null;
		Usuario receptor  = null;
		String  texto     = "";
		// .+\"usuarioId\":\"(?<usuarioId>\d+)\".+\"manitasId\":\"(?<manitasId>\d+)\".+\"mensaje\":\"(?<mensaje>.+)\",.+
		// http://www.regexplanet.com/advanced/java/index.html
		Pattern p = Pattern.compile(".+\\\"usuarioId\\\":\\\"(?<usuarioId>\\d+)\\\".+\\\"manitasId\\\":\\\"(?<manitasId>\\d+)\\\".+\\\"mensaje\\\":\\\"(?<mensaje>.+)\\\",.+");
		Matcher m = p.matcher(mensajeChatJSON);
		if (m.find()) {
			emisor   = db.getUsuario(Integer.parseInt(m.group("usuarioId")));
			receptor = db.getUsuario(Integer.parseInt(m.group("manitasId")));
			texto    = m.group("mensaje");
			System.out.println("emisor="   + emisor);
			System.out.println("receptor=" + receptor);
			System.out.println("texto="    + texto);
		}
		Mensaje mensajeRecibido = new Mensaje(emisor, receptor);
		mensajeRecibido.setTimestamp(new Date());
		mensajeRecibido.setTexto(texto);
//		String  urlImagen = null;
//		if (urlImagen != null) {
//			mensajeRecibido.setImagen(urlImagen);
//		}
		return mensajeRecibido;
	}
	
}
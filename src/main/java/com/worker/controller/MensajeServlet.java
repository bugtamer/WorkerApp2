package com.worker.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

@WebServlet(urlPatterns = "/mensaje2")
public class MensajeServlet extends WebSocketServlet { 
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(MensajeServlet.class); 


	@Override
	protected boolean verifyOrigin(String origin) {
		System.out.println("verifyOrigin(String origin)=" + origin);
		log.trace("Origin: {}", origin);
		return true;
	} 
	
	
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
		return new WebSocketConnection();
	}
	
	
	private static class WebSocketConnection extends MessageInbound { 
		@Override
		protected void onOpen(WsOutbound outbound) {
			System.out.println("Conexión abierta");
			log.info("Conexión abierta");
		} 
		
		
		@Override
			protected void onClose(int status) {
			System.out.println("Conexión cerrada");
			log.info("Conexión cerrada");
		} 
		
		
		@Override
		protected void onBinaryMessage(ByteBuffer byteBuffer) throws IOException {
			System.out.println("No se soportan mensajes binarios");
			log.warn("No se soportan mensajes binarios");
			throw new UnsupportedOperationException("No se soportan mensajes binarios");
		}
		
		
		@Override
		protected void onTextMessage(CharBuffer charBuffer) throws IOException {
			final String user = charBuffer.toString();
			System.out.println("Mensaje recibido: {}" + user);
			log.debug("Mensaje recibido: {}", user);
			getWsOutbound().writeTextMessage(CharBuffer.wrap("Hola " + user + " desde WebSocket"));
		}
	}
}
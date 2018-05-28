package com.worker.controller;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


/**
 * source: http://pance.mk/index.php/websockets-in-java/
 */
@ServerEndpoint("/mensaje")
public class MensajeEEWebSocketEndpoint {
 
  @OnOpen
  public void onOpen(Session session) throws IOException {
    //Handle new connection here
    session.getBasicRemote().sendText("Connected!");
  }
 
  
  @OnMessage
  public void onMessage(String message) {
    //Handle client received message here
  }
 
  
  @OnClose
  public void onClose(Session session, CloseReason reason) {
    //Handle closing connection here
  }
 
  
  @OnError
  public void onError(Session session, Throwable throwable) {
    //Handle error during transport here
  }
  
}
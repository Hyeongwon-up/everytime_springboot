package com.example.toy.config.webSocket;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

  private static List<WebSocketSession> webSocketSessionList = new ArrayList<>();

  @Override
  protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
    String payload = message.getPayload();
    log.info("payload: {}", payload);

    for(WebSocketSession session : webSocketSessionList) {
      session.sendMessage(message);
    }
  }


  // client 가 접속 시 호출되는 메서드
  @Override
  public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {

    webSocketSessionList.add(webSocketSession);
    log.info(webSocketSession + "클라이언트 접속");
  }

  // client 가 접속 해제시 호출되는 메서드

  @Override
  public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
    log.info(webSocketSession + "클라이언트 접속 해제");
    webSocketSessionList.remove(webSocketSessionList);

  }

}

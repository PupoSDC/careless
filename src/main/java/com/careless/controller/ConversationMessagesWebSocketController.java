package com.careless.controller;

import com.careless.model.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;


@Controller
public class ConversationMessagesWebSocketController {

  @MessageMapping("/conversation/sendMessage")
  @SendTo("/topic/public")
  public String greeting(@Payload Message message) throws Exception {
    return "Hello, World: " + message;
  }


}

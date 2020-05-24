package com.careless.controller;

import com.careless.model.Conversation;
import com.careless.repository.ConversationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConversationController {

  @Autowired private ConversationRepository conversations;

  @PostMapping("/conversations")
  Conversation createConversation(List<String> participants) {
    return new Conversation().setParticipants(participants);
  }

  @GetMapping("/conversations")
  List<Conversation> getConversationsForCurrentUser() {
    var participantId = "1234";
    return conversations.findByParticipant(participantId);
  }
}

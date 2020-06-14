package com.careless.controller;

import com.careless.model.Conversation;
import com.careless.model.Message;
import com.careless.repository.MessageRepository;
import com.careless.service.ConversationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

  @Autowired private ConversationService conversationService;
  @Autowired private MessageRepository messageService;

  @GetMapping
  List<Conversation> getConversationsOfAuthenticatedUser(Authentication authentication) {
    return conversationService.findConversationsOfCurrentUser(authentication);
  }

  @GetMapping("/{conversationId}")
  Conversation getReactiveConversationFromId(
      @PathVariable("conversationId") String conversationId) {
    return conversationService.findConversationById(conversationId);
  }

  @PostMapping("/{conversationId}")
  void addNewMessage(
      @PathVariable("conversationId") String conversationId,
      @RequestBody Message message,
      Authentication authentication) {
    conversationService.saveMessageToConversation(
        conversationId,
        message.getText(),
        authentication);
  }



/**
  @PostMapping
  void createConversation(@RequestBody List<String> participants, Authentication authentication) {
    var currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
    if (!participants.contains(currentUserId)) {
      participants.add(currentUserId);
    }
    conversationService.createNewConversation(participants);
  }

 **/
}

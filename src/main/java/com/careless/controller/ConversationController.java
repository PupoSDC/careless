package com.careless.controller;

import com.careless.config.security.service.UserDetailsImpl;
import com.careless.model.Conversation;
import com.careless.service.ConversationService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

  @Autowired private ConversationService conversationService;

  @GetMapping
  List<Conversation> getConversationsOfAuthenticatedUser(Authentication authentication) {
    var currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
    return conversationService.getConversationsFromUserId(currentUserId);
  }

  @PostMapping
  void createConversation(@RequestBody List<String> participants, Authentication authentication) {
    var currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
    if (!participants.contains(currentUserId)) {
      participants.add(currentUserId);
    }
    conversationService.createNewConversation(participants);
  }

  @PostMapping("/{conversationId}")
  void postNewMessage(
      @PathVariable("conversationId") String conversationId,
      @RequestBody String messageText,
      Authentication authentication) {
    conversationService.saveNewMessageToConversation(conversationId, messageText, authentication);
  }
}

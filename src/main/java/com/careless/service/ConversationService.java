package com.careless.service;

import com.careless.exceptions.rest.ConversationAlreadyExistsException;
import com.careless.model.Conversation;
import com.careless.model.Message;
import com.careless.model.Person;
import com.careless.repository.ConversationRepository;
import com.careless.repository.MessageRepository;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
  @Autowired private MessageRepository reactiveMessageRepository;
  @Autowired private ConversationRepository conversationRepository;
  @Autowired private PersonService personService;

  public void createNewConversationForParticipants(List<Person> participants, String name) {
    var participantIds = participants
        .stream()
        .map(Person::getId)
        .collect(Collectors.toList());
    createNewConversationForParticipantIds(participantIds, name);
  }

  public List<Conversation> findConversationsOfCurrentUser(Authentication authentication) {
    var currentUser = personService.getCurrentUserFromAuthentication(authentication);
    return conversationRepository.findByParticipantsContaining(currentUser.getId());
  }

  public Conversation findConversationById(String id) {
    return conversationRepository.findConversationById(id);
  }

  public void saveMessageToConversation(String conversationId, String messageText, Authentication authentication) {
    var currentUser = personService.getCurrentUserFromAuthentication(authentication);
    var message = new Message()
        .setSender(currentUser.getId())
        .setText(messageText)
        .setDate(new Date());

    reactiveMessageRepository.saveMessage(message, conversationId);
  }

  private void createNewConversationForParticipantIds(List<String> participantIds, String name) {
    if (isUniqueConversation(name)) {
      throw new ConversationAlreadyExistsException(name);
    }
    var newConversation = new Conversation()
        .setParticipants(participantIds)
        .setName(name)
        .setMessages(Collections.emptyList());
    conversationRepository.save(newConversation);
  }

  private boolean isUniqueConversation(String name) {
    return conversationRepository.existsByName(name);
  }
}

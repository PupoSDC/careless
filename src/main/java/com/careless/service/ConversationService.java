package com.careless.service;

import com.careless.config.security.service.UserDetailsImpl;
import com.careless.exceptions.rest.ConversationNotFoundException;
import com.careless.exceptions.rest.PersonNotFoundException;
import com.careless.model.Conversation;
import com.careless.model.Message;
import com.careless.model.Person;
import com.careless.repository.ConversationRepository;
import com.careless.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ConversationService {
  @Autowired private ConversationRepository conversationRepository;
  @Autowired private UserRepository userRepository;

  public void createNewConversation(List<String> participantIds) {
    var participants =
        participantIds
            .stream()
            .map(participantId -> userRepository.findById(participantId))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    var newConversation = new Conversation().setParticipants(participantIds);
    conversationRepository
        .save(newConversation)
        .doOnSuccess(conversation -> addNewCoversationToParticipants(conversation, participants))
        .subscribe();
  }

  public List<Conversation> getConversationsFromUserId(String userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new PersonNotFoundException(userId))
        .getConversations();
  }
  public Mono<Conversation> findConversation(@NotNull String conversationId, Authentication user) {
    var currentUserId = ((UserDetailsImpl) user.getPrincipal()).getId();
    return conversationRepository
        .findByIdAndUserAsParticipant(conversationId, currentUserId)
        .switchIfEmpty(Mono.error(new ConversationNotFoundException(conversationId)));
  }

  public void saveNewMessageToConversation(
      @NotNull String conversationId, @NotNull String text, Authentication user) {
    var currentUserId = ((UserDetailsImpl) user.getPrincipal()).getId();
    var message = new Message()
        .setDate(new Date())
        .setText(text)
        .setSender(currentUserId);
    conversationRepository
        .findByIdAndUserAsParticipant(conversationId, currentUserId)
        .switchIfEmpty(Mono.error(new ConversationNotFoundException(conversationId)))
        .doOnSuccess(
            conversation -> {
              conversation.getMessages().add(message);
              conversationRepository.save(conversation);
            })
        .subscribe();
  }

  private void addNewCoversationToParticipants(
      Conversation newConversation,
      List<Person> participants) {
    participants.forEach(participant -> {
      List<Conversation> conversations = Optional
          .ofNullable(participant.getConversations())
          .orElse(Collections.emptyList());
      List<Conversation> newConversations = new ArrayList<>(conversations);
      newConversations.add(newConversation);
      participant.setConversations(newConversations);
      userRepository.save(participant);
    });
  }
}

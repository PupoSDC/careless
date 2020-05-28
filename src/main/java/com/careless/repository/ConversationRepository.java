package com.careless.repository;

import com.careless.model.Conversation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ConversationRepository extends ReactiveMongoRepository<Conversation, String> {

  @Query("{'id': ?0, 'participants': {$elemMatch: {id: ?1}}}")
  Mono<Conversation> findByIdAndUserAsParticipant(String conversationId, String personId);
}

package com.careless.repository;

import com.careless.model.Conversation;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepository extends MongoRepository<Conversation, String>{

  boolean existsByName(String name);

  Conversation findConversationById(String conversationId);

  List<Conversation> findByParticipantsContaining(String personId);

}

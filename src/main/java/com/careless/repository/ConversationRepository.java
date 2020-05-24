package com.careless.repository;

import com.careless.model.Conversation;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ConversationRepository extends MongoRepository<Conversation, String> {

  @Query("{participants: ?0}")
  List<Conversation> findByParticipant(String participant);
}

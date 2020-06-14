package com.careless.repository;

import com.careless.exceptions.rest.ConversationNotFoundException;
import com.careless.model.Conversation;
import com.careless.model.Message;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public void saveMessage(Message message, String conversationId) {
    var criteria = Criteria.where("_id").is(conversationId);
    var query = Query.query(criteria);
    var update = new Update().push(Conversation.MESSAGE, message);
    mongoTemplate.findAndModify(query, update, Conversation.class);
  }

  @Override
  public List<Message> getMessages(String conversationId) {
    var criteria = Criteria.where("_id").is(conversationId);
    var query = Query.query(criteria);
    return Optional.of(mongoTemplate.findOne(query, Conversation.class))
        .orElseThrow(() -> new ConversationNotFoundException(conversationId))
        .getMessages();
  }
}

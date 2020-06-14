package com.careless.repository;

import com.careless.model.Message;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface MessageRepository {

  @Transactional
  void saveMessage(Message message, String conversationId);
  
  List<Message> getMessages(String conversationId);
}

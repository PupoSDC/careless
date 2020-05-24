package com.careless.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document(collection = "conversations")
public class Conversation {
  @Id private String id;
  private List<String> participants;
  private List<Message> messages;
}

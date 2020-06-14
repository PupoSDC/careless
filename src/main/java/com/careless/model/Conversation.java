package com.careless.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document(collection = "conversations")
public class Conversation {
  public static final String MESSAGE ="messages";

  @Id private String id;

  private String name;

  /** Contains person IDs **/
  private List<String> participants;

  @JsonIgnore
  private List<Message> messages;
}

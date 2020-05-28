package com.careless.model;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class Message {
  @Id private String id;
  private String text;
  private String sender;
  private Date date;
  /** Contains person IDs **/
  private List<String> readers;
}

package com.careless.model;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Message {
  private String text;
  private String sender;
  private Date date;
  private List<String> personsWhoReadMessage;
}

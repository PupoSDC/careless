package com.careless.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "roles")
public class Role {
  public enum EROLE {
    ROLE_USER,
    ROLE_ADMIN,
  }

  @Id private String id;

  @Indexed(unique = true, direction = IndexDirection.DESCENDING)
  private EROLE name;
}

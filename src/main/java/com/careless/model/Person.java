package com.careless.model;

import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document(collection = "users")
public class Person {
  @Id private String id;

  @Indexed(unique = true, direction = IndexDirection.DESCENDING)
  @NotBlank
  @Size(min = 6, max = 50)
  private String username;

  @NotBlank
  @Size(min = 8, max = 126)
  private String password;

  @DBRef private Set<Role> roles;

  @DBRef private List<Conversation> conversations;
}

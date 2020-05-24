package com.careless.dto;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import net.minidev.json.annotate.JsonIgnore;

@Data
@Accessors(chain = true)
public class JwtResponse {
  private final String type = "Bearer";
  private String token;
  private String username;
  @JsonIgnore private String id;
  @JsonIgnore private List<String> roles;
}

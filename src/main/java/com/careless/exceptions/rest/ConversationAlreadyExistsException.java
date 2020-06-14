package com.careless.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Conversation already exists")
public class ConversationAlreadyExistsException extends RuntimeException {

  public ConversationAlreadyExistsException(String name) {
    super(name);
  }
}

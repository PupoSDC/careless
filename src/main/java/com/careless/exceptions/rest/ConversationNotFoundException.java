package com.careless.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "User already exists")
public class ConversationNotFoundException extends RuntimeException {
  public ConversationNotFoundException(String conversationId) {
    super(conversationId);
  }
}

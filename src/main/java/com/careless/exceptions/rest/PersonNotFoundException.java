package com.careless.exceptions.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Person Not found")
public class PersonNotFoundException extends RuntimeException {
  public PersonNotFoundException(String personId) {
    super(personId);
  }
}
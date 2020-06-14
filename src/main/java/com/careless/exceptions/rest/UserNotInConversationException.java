package com.careless.exceptions.rest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User not in conversation")
public class UserNotInConversationException extends RuntimeException {}

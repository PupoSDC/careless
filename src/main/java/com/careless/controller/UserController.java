package com.careless.controller;

import com.careless.config.security.service.UserDetailsServiceImpl;
import com.careless.dto.JwtResponse;
import com.careless.dto.UserCredentials;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired UserDetailsServiceImpl userDetailsService;

  @PostMapping("/register")
  public JwtResponse registerNewUser(@Valid @RequestBody UserCredentials credentials) {
    return userDetailsService.registerUser(credentials);
  }

  @PostMapping("/login")
  public JwtResponse loginUser(@Valid @RequestBody UserCredentials credentials) {
    return userDetailsService.authenticateUser(credentials);
  }
}

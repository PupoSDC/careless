package com.careless.controller;

import com.careless.config.security.service.UserDetailsServiceImpl;
import com.careless.dto.JwtResponse;
import com.careless.dto.UserCredentials;
import com.careless.model.Person;
import com.careless.service.PersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired UserDetailsServiceImpl userDetailsService;
  @Autowired
  PersonService personService;

  @PostMapping("/register")
  public JwtResponse registerNewUser(@Valid @RequestBody UserCredentials credentials) {
    return userDetailsService.registerUser(credentials);
  }

  @PostMapping("/login")
  public JwtResponse loginUser(@Valid @RequestBody UserCredentials credentials) {
    return userDetailsService.authenticateUser(credentials);
  }

  @GetMapping("/list")
  @PreAuthorize("hasRole('USER')")
  public List<Person> findAllUsersByUsername(
      @RequestParam(name = "username", defaultValue = "") String username,
      @RequestParam(name = "page", defaultValue = "0") int page) {
    return personService.getAvailableUsers(username, page);
  }
}

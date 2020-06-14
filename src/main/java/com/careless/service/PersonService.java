package com.careless.service;

import com.careless.config.security.service.UserDetailsImpl;
import com.careless.exceptions.rest.PersonNotFoundException;
import com.careless.model.Person;
import com.careless.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
  @Autowired private PersonRepository personRepository;

  public  Person getCurrentUserFromAuthentication(Authentication authentication) {
    var currentUserId = ((UserDetailsImpl) authentication.getPrincipal()).getId();
    return personRepository
        .findById(currentUserId)
        .orElseThrow(() -> new PersonNotFoundException(currentUserId));
  }

  public List<Person> getAllPersons() {
    return personRepository.findAll();
  }

  public List<Person> getAvailableUsers(String username, int page) {
    return personRepository.findAllByUsernameLikeOrderByUsername(username);
  }
}

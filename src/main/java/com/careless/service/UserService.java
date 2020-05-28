package com.careless.service;

import com.careless.dto.User;
import com.careless.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired private UserRepository userRepository;

  public List<User> getAvailableUsers(String username, int page) {
    return userRepository
        .findAllByUsernameLikeOrderByUsername(username, PageRequest.of(page, 20))
        .stream()
        .map(person -> new User().setId(person.getId()).setUsername(person.getUsername()))
        .collect(Collectors.toList());
  }
}

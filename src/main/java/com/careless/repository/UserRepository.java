package com.careless.repository;

import com.careless.model.Person;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Person, String> {

  Page<Person> findAllByUsernameLikeOrderByUsername(String username, Pageable pageable);

  Optional<Person> findByUsername(String username);

  Boolean existsByUsername(String username);
}

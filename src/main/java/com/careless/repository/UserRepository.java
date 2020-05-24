package com.careless.repository;

import com.careless.model.Person;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Person, String> {

  Optional<Person> findByUsername(String username);

  Boolean existsByUsername(String username);
}

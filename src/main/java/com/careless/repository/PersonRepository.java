package com.careless.repository;

import com.careless.model.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {

  List<Person> findAllByUsernameLikeOrderByUsername(String username);

  Optional<Person> findByUsername(String username);

  Boolean existsByUsername(String username);
}

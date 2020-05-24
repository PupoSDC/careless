package com.careless.repository;

import com.careless.model.Role;
import com.careless.model.Role.EROLE;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

  Role findByName(EROLE name);
}

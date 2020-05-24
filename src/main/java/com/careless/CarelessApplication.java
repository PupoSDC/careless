package com.careless;

import com.careless.model.Role;
import com.careless.model.Role.EROLE;
import com.careless.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.careless.repository")
public class CarelessApplication {

  public static void main(String[] args) {
    SpringApplication.run(CarelessApplication.class, args);
  }

  @Bean
  CommandLineRunner init(RoleRepository roleRepository) {
    return args -> {
      Role adminRole = roleRepository.findByName(EROLE.ROLE_ADMIN);
      if (adminRole == null) {
        Role newAdminRole = new Role();
        newAdminRole.setName(EROLE.ROLE_ADMIN);
        roleRepository.save(newAdminRole);
      }

      Role userRole = roleRepository.findByName(EROLE.ROLE_USER);
      if (userRole == null) {
        Role newUserRole = new Role();
        newUserRole.setName(EROLE.ROLE_USER);
        roleRepository.save(newUserRole);
      }
    };
  }
}

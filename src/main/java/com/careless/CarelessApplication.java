package com.careless;

import com.careless.exceptions.rest.ConversationAlreadyExistsException;
import com.careless.model.Role;
import com.careless.model.Role.EROLE;
import com.careless.repository.RoleRepository;
import com.careless.service.ConversationService;
import com.careless.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Slf4j
@SpringBootApplication
@EnableMongoRepositories("com.careless.repository")
public class CarelessApplication {
  private static final String TOWN_SQUARE = "Town Square";

  public static void main(String[] args) {
    SpringApplication.run(CarelessApplication.class, args);
  }

  @Bean
  CommandLineRunner init(
      RoleRepository roleRepository,
      PersonService userService,
      ConversationService conversationService) {
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

      try {
        var allPersons = userService.getAllPersons();
        conversationService.createNewConversationForParticipants(allPersons, TOWN_SQUARE);
        log.info("created " + TOWN_SQUARE);
      } catch (ConversationAlreadyExistsException e) {
        log.info("Conversation '" + TOWN_SQUARE + "' Was already present");
      }
    };
  }
}

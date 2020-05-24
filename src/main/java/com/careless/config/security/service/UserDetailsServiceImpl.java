package com.careless.config.security.service;

import com.careless.config.security.jwt.JwtUtils;
import com.careless.dto.JwtResponse;
import com.careless.dto.UserCredentials;
import com.careless.exceptions.rest.UserAlreadyExistsException;
import com.careless.model.Person;
import com.careless.model.Role;
import com.careless.model.Role.EROLE;
import com.careless.repository.RoleRepository;
import com.careless.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired UserRepository userRepository;
  @Autowired AuthenticationManager authenticationManager;
  @Autowired JwtUtils jwtUtils;
  @Autowired PasswordEncoder encoder;
  @Autowired RoleRepository roleRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username));
    return UserDetailsImpl.build(user);
  }

  public JwtResponse authenticateUser(UserCredentials userCredentials) {
    var authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userCredentials.getUsername(), userCredentials.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    var userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles =
        userDetails
            .getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    return new JwtResponse()
        .setId(userDetails.getId())
        .setUsername(userDetails.getUsername())
        .setToken(jwt)
        .setRoles(roles);
  }

  public JwtResponse registerUser(UserCredentials userCredentials) {
    if (userRepository.existsByUsername(userCredentials.getUsername())) {
      throw new UserAlreadyExistsException();
    }

    Role userRole = roleRepository.findByName(EROLE.ROLE_USER);
    Set<Role> defaultRoles = new HashSet<>();
    defaultRoles.add(userRole);

    Person user =
        new Person()
            .setUsername(userCredentials.getUsername())
            .setPassword(encoder.encode(userCredentials.getPassword()))
            .setRoles(defaultRoles);
    userRepository.save(user);
    return authenticateUser(userCredentials);
  }
}

package com.petparade.api.controller;

import com.petparade.api.exception.ResourceNotFoundException;
import com.petparade.api.model.User;
import com.petparade.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {
  private final UserRepository userRepository;

  @Autowired
  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  @GetMapping("users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
    User user = userRepository
        .findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("Could not find user with id::" + userId));

    return ResponseEntity.ok().body(user);
  }

  @GetMapping("users/{email}/{password}")
  public ResponseEntity<User> getUserByLogin(
      @PathVariable(value = "email") String email,
      @PathVariable(value = "password") String password
      ) {
    User user = userRepository.findUserByEmailAndPassword(email, password);

    return ResponseEntity.ok().body(user);
  }

}

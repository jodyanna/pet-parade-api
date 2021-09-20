package com.petparade.api.controller;

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
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return (List<User>) userRepository.findAll();
  }

  @GetMapping("users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
    User user = userRepository.findUserById(userId);

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

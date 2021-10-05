package com.petparade.api.controller;

import com.petparade.api.dto.UserDto;
import com.petparade.api.dto.UserRequestDto;
import com.petparade.api.security.MyUserDetailsService;
import com.petparade.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserDto> getAllUsers() {
    return this.userService.findAll();
  }

  @GetMapping("{id}")
  public UserDto getUserById(@PathVariable Long id) {
    return this.userService.findById(id);
  }

  @PostMapping(
      value = "login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<UserDto> loginUser(@RequestBody UserRequestDto request) {
    return ResponseEntity.ok().body(this.userService.findByEmailAndPassword(request.getEmail(), request.getPassword()));
  }

  @PostMapping(value = "signup", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
    userDto.setId(null);
    UserDto body = this.userService.save(userDto);

    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PutMapping
  public UserDto update(@RequestBody UserDto userDto) {
    return this.userService.update(userDto);
  }

  @DeleteMapping("{id}")
  public void deleteUserById(@PathVariable Long id) {
    this.userService.deleteById(id);
  }

}

package com.petparade.api.controller;

import com.petparade.api.dto.UserDto;
import com.petparade.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    return userService.findAll();
  }

  @GetMapping("{id}")
  public UserDto getUserById(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PostMapping
  public UserDto save(@RequestBody UserDto userDto) {
    userDto.setId(null);
    return this.userService.save(userDto);
  }

  @PutMapping
  public UserDto update(@RequestBody UserDto userDto) {
    return this.userService.save(userDto);
  }

  @DeleteMapping("{id}")
  public void deleteUserById(@PathVariable Long id) {
    this.userService.deleteById(id);
  }

}

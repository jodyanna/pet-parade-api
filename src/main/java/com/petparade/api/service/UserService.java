package com.petparade.api.service;

import com.petparade.api.dto.UserDto;

import java.util.List;

public interface UserService {
  UserDto findById(Long id);

  List<UserDto> findAll();

  UserDto save(UserDto userDto);

  UserDto update(UserDto userDto);

  void deleteById(Long id);
}

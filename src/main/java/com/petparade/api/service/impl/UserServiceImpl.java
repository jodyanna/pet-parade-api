package com.petparade.api.service.impl;

import com.petparade.api.dto.UserDto;
import com.petparade.api.exception.ResourceNotFoundException;
import com.petparade.api.model.User;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDto findById(Long id) {
    User user = this.userRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Could not find user with id: " + id));

    return new UserDto(user);
  }

  @Override
  public List<UserDto> findAll() {
    return this.userRepository
        .findAll()
        .stream()
        .map(UserDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto save(UserDto userDto) {
    return null;
  }

  @Override
  public void deleteById(Long id) {
    this.userRepository.deleteById(id);
  }
}

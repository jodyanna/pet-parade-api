package com.petparade.api.service.impl;

import com.petparade.api.dto.UserDto;
import com.petparade.api.exception.ResourceNotFoundException;
import com.petparade.api.model.Pet;
import com.petparade.api.model.Role;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.RatingRepository;
import com.petparade.api.repository.RoleRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PetRepository petRepository;
  private final RoleRepository roleRepository;
  private final RatingRepository ratingRepository;

  @Autowired
  public UserServiceImpl(
      UserRepository userRepository,
      PetRepository petRepository,
      RoleRepository roleRepository,
      RatingRepository ratingRepository
  ) {
    this.userRepository = userRepository;
    this.petRepository = petRepository;
    this.roleRepository = roleRepository;
    this.ratingRepository = ratingRepository;
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
    // Persist user
    User user = this.dtoToEntity(userDto);
    User savedUser = this.userRepository.save(user);

    if (userDto.getId() == null) {
      // Persist new user role
      Role userRole = new Role("USER", savedUser);
      this.roleRepository.save(userRole);

      // Add role (in a set) to the saved user
      Set<Role> userRoles = new HashSet<>(Collections.emptySet());
      userRoles.add(userRole);
      savedUser.setRoles(userRoles);
    }

    return new UserDto(savedUser);
  }

  @Override
  public UserDto update(UserDto userDto) {
    return null;
  }

  @Override
  public void deleteById(Long id) {
    this.userRepository.deleteById(id);
  }

  private User dtoToEntity(UserDto userDto) {
    User user = new User();

    user.setId(userDto.getId());
    user.setUsername(userDto.getUsername());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setCity(user.getCity());
    user.setState(user.getState());

    if (userDto.getDateCreated() == null) {
      user.setDateCreated(new Date());
    } else {
      user.setDateCreated(userDto.getDateCreated());
    }

    // user.setDateModified();

    // roles
    if (userDto.getRoles() != null) {
      user.setRoles(this.roleRepository.getRolesByUserId(userDto.getId()));
    }

    // pets
    user.setPets(this.petRepository.getAllPetsByUserId(userDto.getId()));

    // likedPets
    Set<Pet> likedPets = new HashSet<>(Collections.emptySet());
    if (userDto.getLikedPets() != null) {
      for (Long id : userDto.getLikedPets()) {
        this.petRepository.findById(id).ifPresent(likedPets::add);
      }
    }
    user.setLikedPets(likedPets);

    // ratings
    user.setRatings(this.ratingRepository.getAllRatingsByUserId(userDto.getId()));

    return user;
  }
}

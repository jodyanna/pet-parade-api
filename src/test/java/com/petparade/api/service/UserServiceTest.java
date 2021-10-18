package com.petparade.api.service;

import com.petparade.api.dto.UserDto;
import com.petparade.api.model.Pet;
import com.petparade.api.model.Rating;
import com.petparade.api.model.Role;
import com.petparade.api.model.User;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.repository.RatingRepository;
import com.petparade.api.repository.RoleRepository;
import com.petparade.api.repository.UserRepository;
import com.petparade.api.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;
  @Mock
  private PetRepository petRepository;
  @Mock
  private RoleRepository roleRepository;
  @Mock
  private RatingRepository ratingRepository;
  @Mock
  private UserDto userDto;
  @Mock
  private User user;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    this.userDto = new UserDto("test", "test@email.com", "password");
    this.user = new User();
    user.setId(1L);
    user.setUsername("test");
    user.setEmail("test@email.com");
    user.setPassword("password");
    user.setDateCreated(new Date(1));

    Set<Role> roles = new HashSet<>();
    user.setRoles(roles);

    Set<Pet> pets = new HashSet<>();
    user.setPets(pets);

    Set<Pet> likedPets = new HashSet<>();
    user.setLikedPets(likedPets);

    Set<Rating> ratings = new HashSet<>();
    user.setRatings(ratings);
  }

  @Test
  public void findById() {
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    UserDto result = userService.findById(1L);

    // then
    assertEquals("test", result.getUsername());
    assertEquals("test@email.com", result.getEmail());
    assertEquals("password", result.getPassword());
  }

  @Test
  public void findByUsernameAndPassword() {
    // when
    when(userRepository.findByEmailAndPassword(anyString(), anyString()))
        .thenReturn(Optional.ofNullable(user));
    UserDto result = userService.findByEmailAndPassword("test@email.com", "password");

    // then
    assertEquals("test", result.getUsername());
    assertEquals("test@email.com", result.getEmail());
    assertEquals("password", result.getPassword());
  }

  @Test
  public void findAll() {
    List<User> users = Collections.singletonList(user);
    when(userRepository.findAll()).thenReturn(users);

    List<UserDto> result = userService.findAll();

    assertEquals(1, result.size());
  }

  @Test
  public void save() {
    when(userRepository.save(any())).thenReturn(user);
    when(roleRepository.save(any())).thenReturn(new Role("ROLE_USER", user));

    UserDto result = userService.save(userDto);

    assertEquals("test", result.getUsername());
    assertEquals("test@email.com", result.getEmail());
    assertEquals("password", result.getPassword());
    assertEquals("ROLE_USER", result.getRoles().get(0));
  }

  @Test
  public void update() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
    when(userRepository.save(any())).thenReturn(user);

    userDto.setId(1L);

    UserDto result = userService.update(userDto);

    assertEquals("test", result.getUsername());
    assertEquals("test@email.com", result.getEmail());
    assertEquals("password", result.getPassword());
  }
}

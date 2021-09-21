package com.petparade.api.dto;

import com.petparade.api.model.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
  private Long id;
  private String username;
  private String email;
  private String city;
  private String state;
  private Date dateCreated;
  private List<String> roles;
  private List<Long> pets;
  private List<Long> likedPets;
  private List<RatingDto> ratings;

  public UserDto(User user) {
    this.setId(user.getId());
    this.setUsername(user.getUsername());
    this.setEmail(user.getEmail());
    this.setCity(user.getCity());
    this.setState(user.getState());
    this.setDateCreated(user.getDateCreated());

    // Set roles as list of Strings
    List<String> userRoles = new ArrayList<>();
    for (Role role : user.getRoles()) {
      userRoles.add(role.getRole());
    }
    this.setRoles(userRoles);

    // Set user pets as list of ids
    List<Long> userPets = new ArrayList<>();
    for (Pet pet : user.getPets()) {
      userPets.add(pet.getId());
    }
    this.setPets(userPets);

    // Set user liked pets as list of ids
    List<Long> userLikedPets = new ArrayList<>();
    for (Like like : user.getLikedPets()) {
      userLikedPets.add(like.getPet().getId());
    }
    this.setLikedPets(userLikedPets);

    // Set user ratings as list of rating dto
    List<RatingDto> userRatings = new ArrayList<>();
    for (Rating rating : user.getRatings()) {
      RatingDto ratingDto = new RatingDto(rating);
      userRatings.add(ratingDto);
    }
    this.setRatings(userRatings);

  }
}
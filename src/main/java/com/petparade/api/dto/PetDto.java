package com.petparade.api.dto;

import com.petparade.api.model.Pet;
import com.petparade.api.model.Rating;
import com.petparade.api.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PetDto {
  private Long id;
  private String name;
  private String bio;
  private Date birthday;
  private Boolean isFlagged;
  private Date dateCreated;
  private String image;
  private String species;
  private Long owner;
  private List<RatingDto> ratings;
  private List<Long> likingUsers;

  public PetDto(Pet pet) {
    this.setId(pet.getId());
    this.setName(pet.getName());
    this.setBio(pet.getBio());
    this.setBirthday(pet.getBirthday());
    this.setIsFlagged(pet.getFlagged());
    this.setDateCreated(pet.getDateCreated());
    this.setImage(pet.getImage());
    this.setSpecies(pet.getSpecies().getName());
    this.setOwner(pet.getUser().getId());

    // Set pet ratings with a list of rating dto
    List<RatingDto> petRatings = new ArrayList<>();
    for (Rating rating : pet.getRatings()) {
      petRatings.add(new RatingDto(rating));
    }
    this.setRatings(petRatings);

    // Set liking users with list of user ids
    List<Long> petLikingUsers = new ArrayList<>();
    for (User user : pet.getUserLikes()) {
      petLikingUsers.add(user.getId());
    }
    this.setLikingUsers(petLikingUsers);
  }
}

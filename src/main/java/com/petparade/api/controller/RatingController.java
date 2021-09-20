package com.petparade.api.controller;

import com.petparade.api.model.Rating;
import com.petparade.api.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class RatingController {
  private final RatingRepository ratingRepository;

  @Autowired
  public RatingController(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }

  @GetMapping("/ratings")
  public List<Rating> getAllRatings() {
    return (List<Rating>) ratingRepository.findAll();
  }

  @GetMapping("/ratings/{id}")
  public List<Rating> getAllUserRatings(@PathVariable(value = "id") Long id) {
    return ratingRepository.findAllByUserId(id);
  }
}

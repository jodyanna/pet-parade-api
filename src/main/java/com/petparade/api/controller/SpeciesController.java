package com.petparade.api.controller;

import com.petparade.api.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("species")
public class SpeciesController {
  private final SpeciesService speciesService;

  @Autowired
  public SpeciesController(SpeciesService speciesService) {
    this.speciesService = speciesService;
  }

  @GetMapping
  public List<String> getAllSpecies() {
    return this.speciesService.findAll();
  }
}

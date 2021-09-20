package com.petparade.api.controller;

import com.petparade.api.exception.ResourceNotFoundException;
import com.petparade.api.model.Pet;
import com.petparade.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PetController {
  private final PetRepository petRepository;

  @Autowired
  public PetController(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @GetMapping("/pets")
  public List<Pet> getAllPets() {
    return (List<Pet>) petRepository.findAll();
  }

  @GetMapping("/pets/{id}")
  public ResponseEntity<Pet> getPetById(@PathVariable(value = "id") Long petId) {
    Pet pet = petRepository.findById(petId)
        .orElseThrow(() -> new ResourceNotFoundException("Could not find pet with id::" + petId));

    return ResponseEntity.ok().body(pet);
  }
}

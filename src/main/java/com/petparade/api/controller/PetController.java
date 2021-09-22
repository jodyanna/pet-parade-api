package com.petparade.api.controller;

import com.petparade.api.dto.PetDto;
import com.petparade.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pets")
public class PetController {
  private final PetService petService;

  @Autowired
  public PetController(PetService petService) {
    this.petService = petService;
  }

  @GetMapping
  public List<PetDto> getAllPets() {
    return this.petService.findAll();
  }

  @GetMapping("{id}")
  public PetDto getPetById(@PathVariable Long id) {
    return this.petService.findById(id);
  }

  @PostMapping
  public PetDto save(@RequestBody PetDto petDto) {
    petDto.setId(null);
    return this.petService.save(petDto);
  }

  @PutMapping
  public PetDto update(@RequestBody PetDto petDto) {
    return this.petService.save(petDto);
  }

  @DeleteMapping("{id}")
  public void deletePetById(@PathVariable Long id) {
    this.petService.deleteById(id);
  }

}

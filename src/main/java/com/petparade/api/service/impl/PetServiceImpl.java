package com.petparade.api.service.impl;

import com.petparade.api.dto.PetDto;
import com.petparade.api.exception.ResourceNotFoundException;
import com.petparade.api.model.Pet;
import com.petparade.api.repository.PetRepository;
import com.petparade.api.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {
  private final PetRepository petRepository;

  @Autowired
  public PetServiceImpl(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  public PetDto findById(Long id) {
    Pet pet = this.petRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Could not find pet with id: " + id));

    return new PetDto(pet);
  }

  @Override
  public List<PetDto> findAll() {
    return this.petRepository
        .findAll()
        .stream()
        .map(PetDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public PetDto save(PetDto petDto) {
    return null;
  }

  @Override
  public void deleteById(Long id) {
    this.petRepository.deleteById(id);
  }
}

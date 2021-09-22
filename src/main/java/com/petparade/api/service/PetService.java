package com.petparade.api.service;

import com.petparade.api.dto.PetDto;

import java.util.List;

public interface PetService {
  PetDto findById(Long id);

  List<PetDto> findAll();

  PetDto save(PetDto petDto);

  void deleteById(Long id);
}

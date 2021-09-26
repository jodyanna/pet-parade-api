package com.petparade.api.dto;

import com.petparade.api.model.Species;
import lombok.Data;

@Data
public class SpeciesDto {
  private Long id;
  private String name;

  public SpeciesDto(Species species) {
    this.setId(species.getId());
    this.setName(species.getName());
  }
}

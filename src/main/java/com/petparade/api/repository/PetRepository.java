package com.petparade.api.repository;

import com.petparade.api.model.Pet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "pets", path = "pets")
public interface PetRepository extends PagingAndSortingRepository<Pet, Long> {
  Optional<Pet> findById(@Param("id") Long id);
}

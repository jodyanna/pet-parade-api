package com.petparade.api.repository;

import com.petparade.api.model.Pet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends PagingAndSortingRepository<Pet, Long> {

}

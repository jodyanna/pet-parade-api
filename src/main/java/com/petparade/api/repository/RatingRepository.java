package com.petparade.api.repository;

import com.petparade.api.model.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends PagingAndSortingRepository<Rating, Long> {
  @Query(value = "SELECT r FROM Rating AS r WHERE r.user.id=:id")
  List<Rating> findAllByUserId(Long id);
}

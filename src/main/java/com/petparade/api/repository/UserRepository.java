package com.petparade.api.repository;

import com.petparade.api.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  User findUserByEmailAndPassword(String email, String password);
}

package com.petparade.api.repository;

import com.petparade.api.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  User findByUsername(@Param("username") String username);

  User findUserById(@Param("id") Long id);

  User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

package com.petparade.api.repository;

import com.petparade.api.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  User findByUsername(@Param("username") String username);

  Optional<User> findById(@Param("id") Long id);

  User findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}

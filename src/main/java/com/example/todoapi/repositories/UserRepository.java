package com.example.todoapi.repositories;

import com.example.todoapi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    @Override
    List<User> findAll();

    Optional<User> findUserByEmail (String email);
}

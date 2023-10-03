package com.example.todoapi.repositories;

import com.example.todoapi.models.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TodoRepository extends CrudRepository<Todo, UUID> {
    @Override
    List<Todo> findAll();
    List<Todo> findAllByUserId(UUID userId);
}

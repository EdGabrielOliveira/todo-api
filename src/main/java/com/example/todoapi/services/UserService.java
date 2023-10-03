package com.example.todoapi.services;

import com.example.todoapi.dtos.RegisterUserDto;
import com.example.todoapi.dtos.UpdateUserDto;
import com.example.todoapi.exceptions.NotFoundException;
import com.example.todoapi.models.Todo;
import com.example.todoapi.models.User;
import com.example.todoapi.repositories.TodoRepository;
import com.example.todoapi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository repository, ModelMapper modelMapper, TodoRepository todoRepository) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.todoRepository = todoRepository;
    }

    // LIST ALL USERS //
    public List<User> list(){
        return repository.findAll();
    }

    // SEARCHER USER BY ID //
    public User getById( UUID id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    // CREATE USER //
    public User registerUser(RegisterUserDto dto){
        User user = new User();
        modelMapper.map(dto, user);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        return repository.save(user);
    }

    // DELETE USER //
    public void delete( UUID id){
      repository.deleteById(id);
    }

    // LIST ALL TODO OF A USER //
    public List<Todo> todosList(UUID id) {
        User user = getById(id);
        return todoRepository.findAllByUserId(id);
    }

    // UPDATE USER BY ID //
    public User update(UUID id, UpdateUserDto dto){
        User user = getById(id);

        modelMapper.map(dto, user);
        return repository.save(user);
    }
}

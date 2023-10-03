package com.example.todoapi.controllers;

import com.example.todoapi.models.Todo;
import com.example.todoapi.models.User;
import com.example.todoapi.dtos.RegisterUserDto;
import com.example.todoapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // CONSTRUCTOR
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET ALL USER
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.list());

    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable UUID id){
        return ResponseEntity.ok().body(userService.getById(id));
    }

    // CREATE USER
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Validated RegisterUserDto dto){
        return ResponseEntity.ok().body(userService.registerUser(dto));
    }

    // DELETE USER BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    // LIST ALL TODO OF A USER
    @GetMapping("/{id}/todos")
    public ResponseEntity<List<Todo>> todosList(@PathVariable UUID id) {
        return ResponseEntity.ok().body(userService.todosList(id));
    }
}

package com.example.todoapi.controllers;

import com.example.todoapi.dtos.RegisterTodoDto;
import com.example.todoapi.dtos.UpdateTodoDto;
import com.example.todoapi.models.Todo;
import com.example.todoapi.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Get all ID
    @GetMapping
    public ResponseEntity<List<Todo>> getAll(){
        return  ResponseEntity.ok().body(todoService.getAll());
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable UUID id){
        return  ResponseEntity.ok().body(todoService.getById(id));
    }

    // Create TODO
    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody @Validated RegisterTodoDto dto){
        return ResponseEntity.ok().body(todoService.create(dto));
    }

    // Update TODO
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable UUID id, @RequestBody @Validated UpdateTodoDto dto){
        return ResponseEntity.ok().body(todoService.update(id,dto));
    }

    //Delete TODO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        todoService.delete(id);
        return ResponseEntity.ok().build();
    }

    //Complete TODO
    @PutMapping("/{id}/complete")
    public ResponseEntity<Todo> completeTodo(@PathVariable UUID id){
        return ResponseEntity.ok().body(todoService.completeTodo(id));
    }
}

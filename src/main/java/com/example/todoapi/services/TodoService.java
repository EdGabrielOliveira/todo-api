package com.example.todoapi.services;

import com.example.todoapi.dtos.RegisterTodoDto;
import com.example.todoapi.dtos.UpdateTodoDto;
import com.example.todoapi.exceptions.NotFoundException;
import com.example.todoapi.models.Todo;
import com.example.todoapi.models.User;
import com.example.todoapi.repositories.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public TodoService(TodoRepository repository, UserService userService, ModelMapper modelMapper) {
        this.todoRepository = repository;
        this.userService =  userService;
        this.modelMapper = modelMapper;
    }


    public List<Todo> getAll(){
        return todoRepository.findAll();
    }

    // Get by ID
    public Todo getById(UUID id) {
        return todoRepository.findById(id).orElseThrow(() -> new NotFoundException("Todo not found"));
    }

    // Create TODO
    public Todo create( RegisterTodoDto dto){

        Todo todo = new Todo();
        modelMapper.map(dto, todo);

        var userId = UUID.fromString(dto.getUserId());

        User user = userService.getById(userId);
        todo.setUser(user);

        return todoRepository.save(todo);

    }

    // Update TODO
    public Todo update(UUID id, UpdateTodoDto dto){
        Todo todo = getById(id);
        modelMapper.map(dto, todo);

        if(dto.getUserId() != null){
            User user = getById(id).getUser();
            todo.setUser(user);
        }
        return todoRepository.save(todo);
    }

    //Delete TODO
    public void delete(UUID id){
       todoRepository.deleteById(id);
    }

    //Complete TODO

    public Todo completeTodo(UUID id){
        Todo todo = getById(id);
        todo.setCompleted(true);

        return todoRepository.save(todo);
    }
}

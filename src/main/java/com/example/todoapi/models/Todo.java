package com.example.todoapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "todos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private  boolean completed = false;

    @ManyToOne
    private User user;
}

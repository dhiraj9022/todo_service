package com.todo.controller;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import com.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> saveTodo(@RequestBody TodoDto todoDto){
        return ResponseEntity.ok(todoService.saveTodo(todoDto));
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodo(@PathVariable(name = "todoId") int todoId){
        return  ResponseEntity.ok(todoService.getTodo(todoId));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo(){
        return  ResponseEntity.ok(todoService.getAllTodo());
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@RequestBody TodoDto todoDto, @PathVariable(name = "todoId") int todoId){
        return  ResponseEntity.ok(todoService.updateTodo(todoDto,todoId));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable(name = "todoId") int todoId){
        todoService.deleteTodo(todoId);
       return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAllTodo(){
        todoService.deleteAllTodo();
        return ResponseEntity.noContent().build();
    }




}


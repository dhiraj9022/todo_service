package com.todo.service;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;

import java.util.List;

public interface TodoService {

    Todo saveTodo(TodoDto todoDto);

    Todo updateTodo(TodoDto todoDto, int todoId);

    Todo getTodo(int todoId);

    List<Todo> getAllTodo();

    void deleteTodo(int todoId);

    void deleteAllTodo();
}

package com.todo.service;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;

public interface TodoService {

    Todo saveTodo(TodoDto todoDto);

    Todo updateTodo(TodoDto todoDto, int todoId);

    Todo getTodo(int todoId);

    Todo getAllTodo();

    void deleteTodo(int todoId);

    void deleteAllTodo();
}

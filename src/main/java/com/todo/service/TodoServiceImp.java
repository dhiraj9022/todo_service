package com.todo.service;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImp implements TodoService {
    @Override
    public Todo saveTodo(TodoDto todoDto) {

        return null;
    }

    @Override
    public Todo updateTodo(TodoDto todoDto, int todoId) {
        return null;
    }

    @Override
    public Todo getTodo(int todoId) {
        return null;
    }

    @Override
    public Todo getAllTodo() {
        return null;
    }

    @Override
    public void deleteTodo(int todoId) {

    }

    @Override
    public void deleteAllTodo() {

    }
}

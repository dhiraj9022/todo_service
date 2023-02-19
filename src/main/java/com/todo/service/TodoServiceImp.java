package com.todo.service;

import com.todo.dto.TODOSTATUS;
import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import com.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImp implements TodoService {

    Logger logger = LoggerFactory.getLogger(TodoServiceImp.class);

    private TodoRepository todoRepository;

    public TodoServiceImp(TodoRepository todoRepo){
        this.todoRepository= todoRepo;
    }

    @Override
    public Todo saveTodo(TodoDto todoDto) {

        Todo td = new Todo();
        td.setTodoTitle(todoDto.getTodoTitle());
        td.setStatus(TODOSTATUS.COMPLETED);
        td.setDescription(todoDto.getDescription());
        td.setTodoDate(todoDto.getTodoDate());

        logger.info("Todo save successfully !!");
        return todoRepository.save(td);
    }

    @Override
    public Todo updateTodo(TodoDto todoDto, int todoId) {

        Todo tdUpdate = getTodo(todoId);

        tdUpdate.setTodoTitle(todoDto.getTodoTitle());
        tdUpdate.setStatus(TODOSTATUS.COMPLETED);
        tdUpdate.setDescription(todoDto.getDescription());
        tdUpdate.setTodoDate(todoDto.getTodoDate());

        logger.info("Todo updated successfully !!");

        return todoRepository.save(tdUpdate);

    }

    @Override
    public Todo getTodo(int todoId) {
        return todoRepository.findById(todoId).orElseThrow(null);
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public void deleteTodo(int todoId) {
        Todo todoDelete = getTodo(todoId);
        todoRepository.delete(todoDelete);
    }

    @Override
    public void deleteAllTodo() {
        todoRepository.deleteAll();;
    }
}

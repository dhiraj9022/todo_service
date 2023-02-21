package com.todo.controllertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoReportService;
import com.todo.service.TodoService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.sql.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoReportService todoReportService;

    @Autowired
    private TodoRepository todoRepo;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper  = new ObjectMapper();;

    private Todo todo;

    @BeforeAll
    void beforeAll(){

        TodoDto todoDto = new TodoDto();
        todoDto.setTodoTitle("Surat trip");
        todoDto.setDescription("enjoy every trip");
        todoDto.setTodoDate(new java.util.Date());
        this.todo = todoService.saveTodo(todoDto);

    }

    @AfterAll
    void afterAll(){
        todoRepo.deleteAll();
    }

    @Test
    @DisplayName("Add todo test")
    public void add_todo_test() throws Exception {

        TodoDto todoDto = new TodoDto();
        todoDto.setTodoTitle("Surat trip test");
        todoDto.setDescription("enjoy every trip test");
        todoDto.setTodoDate(new java.util.Date());

        mockMvc.perform(post("/api/v1/todos").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoDto))).andExpect(status().isOk()).andDo(print());

    }

    @Test
    @DisplayName("Get todo test")
    public void get_todo_test() throws Exception {

        mockMvc.perform(get("/api/v1/todos/"+todo.getTodoId()).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

        mockMvc.perform(get("/api/v1/todos/"+ UUID.randomUUID()).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print());

    }

    @Test
    @DisplayName("Get All todo test")
    public void get_all_todo_test() throws Exception {

        mockMvc.perform(get("/api/v1/todos").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("update todo test")
    public void update_todo_test() throws Exception {

        TodoDto updateTodoDto = new TodoDto();
        updateTodoDto.setTodoTitle("Surat trip updated test");
        updateTodoDto.setDescription("enjoy every trip updated test");
        updateTodoDto.setTodoDate(new java.util.Date());

        mockMvc.perform(put("/api/v1/todos/"+todo.getTodoId()).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateTodoDto))).andExpect(status().isOk()).andDo(print());
    }

    @Test
    @DisplayName("delete todo test")
    public void delete_todo_test() throws Exception {

        mockMvc.perform(delete("/api/v1/todos/"+todo.getTodoId()).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andDo(print());

        mockMvc.perform(get("/api/v1/todos/"+ UUID.randomUUID()).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andDo(print());

    }

    @Test
    @DisplayName("Delete All todo test")
    public void delete_all_todo_test() throws Exception {

        mockMvc.perform(delete("/api/v1/todos").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andDo(print());

    }

    @Test
    @DisplayName("Get todo report test")
    public void get_todo_report_test() throws Exception {
        String format = "pdf";

        Mockito.when(todoReportService.exportTodoReport("pdf")).thenReturn(format);

        mockMvc.perform(get("/api/v1/todos/report/"+format).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());

    }



}

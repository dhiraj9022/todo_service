package com.todo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Getter
@Setter
public class TodoDto {

    @NotBlank
    @NotNull
    private String todoTitle;

    @NotNull
    @NotBlank
    private String description;

    private boolean status;

    @NotNull
    @NotBlank
    private Date todoDate;
}

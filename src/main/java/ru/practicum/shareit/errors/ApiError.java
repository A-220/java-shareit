package ru.practicum.shareit.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {

    private Integer error;

    private String msg;
}

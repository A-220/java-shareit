package ru.practicum.shareit.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    Integer error;
    String msg;

}

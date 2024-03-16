package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    Long id;

    @NotNull
    @NotEmpty
    String name;

    @NotNull
    @NotEmpty
    String description;

    @NotNull
    Boolean available;

    User owner;

    ItemRequest request;
}

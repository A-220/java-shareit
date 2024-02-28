package ru.practicum.shareit.item.repo;


import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface ItemRepository {
    Item addItem(User owner, Item item);

    Item updateItem(Item item, Long itemId);

    Item getItemById(Long itemId);

    List<Item> getAllItems(Long userId);

    List<Item> getItemsByText(String text);
}
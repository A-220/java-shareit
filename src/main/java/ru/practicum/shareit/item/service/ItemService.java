package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item addItem(Long ownerId, Item item);

    Item updateItem(Long userId, Item item, Long itemId);

    Item getItemById(Long itemId);

    List<Item> getAllItems(Long userId);

    List<Item> getItemsByText(String text);
}

package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.errors.exceptions.NotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repo.ItemRepository;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    @Override
    public Item addItem(Long userId, Item item) {
        var i = itemRepository.addItem(userService.getUserById(userId), item);
        log.info("Successfully add item with id: {}", i.getId());
        return i;
    }

    @Override
    public Item updateItem(Long userId, Item item, Long itemId) {
        if (!userId.equals(getItemById(itemId).getId())) {
            if (!(item.getAvailable() != null && item.getName() == null))
                throw new NotFoundException("User not  a owner");
        }
        var i = itemRepository.updateItem(item, itemId);
        log.info("Successfully update item with id: {}", i.getId());
        return i;
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemRepository.getItemById(itemId);
    }

    @Override
    public List<Item> getAllItems(Long userId) {
        return itemRepository.getAllItems(userId);
    }

    @Override
    public List<Item> getItemsByText(String text) {
        return itemRepository.getItemsByText(text);
    }
}
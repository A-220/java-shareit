package ru.practicum.shareit.item.repo;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.TemporaryIdGenerator;
import ru.practicum.shareit.errors.exceptions.NotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryItemRepository implements ItemRepository {
    private final List<Item> inMemoryListOfItems = new ArrayList<>();

    @Override
    public Item addItem(User owner, Item item) {
        item.setOwner(owner);
        item.setId(TemporaryIdGenerator.incrementItemId());
        inMemoryListOfItems.add(item);
        return item;
    }

    @Override
    public Item updateItem(Item updatedItem, Long itemId) {
        var existingItem = getItemById(itemId);

        updateItemFields(existingItem, updatedItem);

        return existingItem;
    }

    private void updateItemFields(Item existingItem, Item updatedItem) {
        String name = (updatedItem.getName() == null) ? existingItem.getName() : updatedItem.getName();
        String description = (updatedItem.getDescription() == null) ? existingItem.getDescription() : updatedItem.getDescription();
        Boolean available = (updatedItem.getAvailable() == null) ? existingItem.getAvailable() : updatedItem.getAvailable();

        existingItem.setName(name);
        existingItem.setDescription(description);
        existingItem.setAvailable(available);
    }

    @Override
    public Item getItemById(Long itemId) {
        return inMemoryListOfItems.stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException("Item with ID " + itemId + "doesn't exist."));
    }

    @Override
    public List<Item> getAllItems(Long userId) {
        return inMemoryListOfItems.stream()
                .filter(item -> item.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getItemsByText(String text) {
        if (text.isBlank() || text.isEmpty())
            return Collections.emptyList();

        final String textLowerCase = text.toLowerCase();

        return inMemoryListOfItems.stream()
                .filter(item -> (item.getDescription().toLowerCase().contains(textLowerCase) ||
                        item.getName().toLowerCase().contains(textLowerCase)) && item.getAvailable())
                .collect(Collectors.toList());
    }
}

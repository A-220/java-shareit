package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.errors.ErrorsHandler;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    public static final String X_SHARER_USER_ID = "X-Sharer-User-Id";

    private final ItemService itemService;

    private final ErrorsHandler errorsHandler;

    @PostMapping
    public ItemDto addItem(@RequestHeader(value = X_SHARER_USER_ID) Long ownerId,
                           @Valid @RequestBody Item item,
                           BindingResult bindingResult) {
        errorsHandler.throwValidationExceptionIfErrorsExist(bindingResult);
        return ItemDto.toDto(itemService.addItem(ownerId, item));
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestHeader(value = X_SHARER_USER_ID) Long userId,
                              @RequestBody Item item,
                              @PathVariable Long itemId) {
        return ItemDto.toDto(itemService.updateItem(userId, item, itemId));
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable Long itemId) {
        return ItemDto.toDto(itemService.getItemById(itemId));
    }

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader(value = X_SHARER_USER_ID) Long userId) {
        return itemService.getAllItems(userId).stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<ItemDto> getItemsByText(@PathParam("text") String text) {
        return itemService.getItemsByText(text).stream()
                .map(ItemDto::toDto)
                .collect(Collectors.toList());
    }
}

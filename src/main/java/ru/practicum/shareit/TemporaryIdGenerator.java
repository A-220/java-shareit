package ru.practicum.shareit;

public class TemporaryIdGenerator {
    private static long userId;
    private static long itemId;

    public static long incrementUserId() {
       return ++userId;
    }

    public static long incrementItemId() {
        return ++itemId;
    }
}

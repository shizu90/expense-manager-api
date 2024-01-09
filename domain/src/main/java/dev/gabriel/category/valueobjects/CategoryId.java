package dev.gabriel.category.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class CategoryId extends Identity {
    private CategoryId(UUID value) {
        super(value);
    }

    public static CategoryId create(UUID value) {
        return new CategoryId(value);
    }
}

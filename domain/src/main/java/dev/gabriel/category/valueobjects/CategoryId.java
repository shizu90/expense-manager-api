package dev.gabriel.category.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class CategoryId extends Identity {
    private CategoryId(String value) {
        super(value);
    }

    public static CategoryId create(String value) {
        return new CategoryId(value);
    }
}

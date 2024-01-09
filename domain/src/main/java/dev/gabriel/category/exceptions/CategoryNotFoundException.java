package dev.gabriel.category.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

import java.util.UUID;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(UUID categoryId) {
        super("Category " + categoryId.toString() + " was not found.");
    }
}

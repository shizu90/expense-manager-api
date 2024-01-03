package dev.gabriel.category.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(String categoryId) {
        super("Category " + categoryId + " was not found.");
    }
}

package dev.gabriel.category.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class CategoryAlreadyDeletedException extends AlreadyDeletedException {
    public CategoryAlreadyDeletedException(String id) {
        super("Category " + id + " is already deleted.");
    }
}

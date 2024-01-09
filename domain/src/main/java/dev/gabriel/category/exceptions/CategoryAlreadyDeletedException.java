package dev.gabriel.category.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class CategoryAlreadyDeletedException extends AlreadyDeletedException {
    public CategoryAlreadyDeletedException() {
        super("Category is already deleted.");
    }
}

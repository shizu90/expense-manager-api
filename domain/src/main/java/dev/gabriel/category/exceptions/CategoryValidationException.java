package dev.gabriel.category.exceptions;

import dev.gabriel.shared.exceptions.DataValidationException;

public class CategoryValidationException extends DataValidationException {
    public CategoryValidationException(String field, String message) {
        super("Category validation failed on " + field + ": " + message);
    }
}

package dev.gabriel.category.valueobjects;

import dev.gabriel.category.exceptions.CategoryValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class CategoryName extends ValueObject {
    private final String value;

    private CategoryName(String value) {
        this.value = value;
    }

    public static CategoryName create(String value) {
        return new CategoryName(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 64) {
            throw new CategoryValidationException("Name", "Name must have between 1 and 64 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

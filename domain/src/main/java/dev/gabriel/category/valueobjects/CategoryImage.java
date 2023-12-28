package dev.gabriel.category.valueobjects;

import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class CategoryImage extends ValueObject {
    private final String value;

    private CategoryImage(String value) {
        this.value = value;
    }

    public static CategoryImage create(String value) {
        return new CategoryImage(value);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

package dev.gabriel.shared.valueobjects;

import dev.gabriel.shared.entities.ValueObject;

import java.util.Arrays;
import java.util.List;

public class Identity extends ValueObject {
    private final String value;
    protected Identity(String value) {
        this.value = value;
    }

    public static Identity create(String value) {
        return new Identity(value);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

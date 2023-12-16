package dev.gabriel.valueobjects;

import dev.gabriel.primitives.ValueObject;

import java.util.Arrays;
import java.util.List;

public class Identity extends ValueObject {
    public Long value;

    public Identity(Long value) {
        this.value = value;
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Long[] {value});
    }
}

package dev.gabriel.shared.valueobjects;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public abstract class Identity extends ValueObject {
    protected final String value;
    protected Identity(String value) {
        this.value = value;
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

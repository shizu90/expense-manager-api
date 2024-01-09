package dev.gabriel.shared.valueobjects;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
public abstract class Identity extends ValueObject {
    protected final UUID value;
    protected Identity(UUID value) {
        this.value = value;
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

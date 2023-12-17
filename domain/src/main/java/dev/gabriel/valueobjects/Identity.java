package dev.gabriel.valueobjects;

import dev.gabriel.primitives.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Identity extends ValueObject {
    private Long value;

    private Identity(Long value) {
        this.value = value;
    }

    public static Identity create(Long value) {
        return new Identity(value);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

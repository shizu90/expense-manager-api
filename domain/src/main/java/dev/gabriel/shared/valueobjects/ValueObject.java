package dev.gabriel.shared.valueobjects;

import java.util.List;

public abstract class ValueObject {
    public abstract List<Object> getAtomicValues();

    private boolean valuesAreEqual(ValueObject valueObject) {
        return getAtomicValues().equals(valueObject.getAtomicValues());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ValueObject && valuesAreEqual((ValueObject) obj);
    }

    @Override
    public int hashCode() {
        return getAtomicValues().hashCode();

    }
}

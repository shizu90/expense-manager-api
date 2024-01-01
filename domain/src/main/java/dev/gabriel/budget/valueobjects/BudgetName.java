package dev.gabriel.budget.valueobjects;

import dev.gabriel.budget.exceptions.BudgetValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class BudgetName extends ValueObject {
    private final String value;

    private BudgetName(String value) {
        validate(value);
        this.value = value;
    }

    public static BudgetName create(String value) {
        return new BudgetName(value);
    }

    private void validate(String value) {
        if(value == null || value.length() < 3 || value.length() > 255) {
            throw new BudgetValidationException("Name", "Name must have between 3 and 255 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

package dev.gabriel.budget.valueobjects;

import dev.gabriel.budget.exceptions.BudgetValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class BudgetDescription extends ValueObject {
    private final String value;

    private BudgetDescription(String value) {
        validate(value);
        this.value = value;
    }

    public static BudgetDescription create(String value) {
        return new BudgetDescription(value);
    }

    private void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new BudgetValidationException("Comment", "Comment must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

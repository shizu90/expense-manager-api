package dev.gabriel.validators;

import dev.gabriel.entities.CommonIncome;
import dev.gabriel.entities.RecurringIncome;
import dev.gabriel.entities.enums.IncomeCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class IncomeValidatorTest {
    @Test
    @DisplayName("Validating a common income without constraints violations.")
    public void validateCommonIncomeTestCase1() {
        CommonIncome commonIncome = CommonIncome.create(1L, "Income Name", "Income Comment", 80.0, IncomeCategory.WAGE);
        IncomeValidator incomeValidator = new IncomeValidator();

        List<String> errors = incomeValidator.validate(commonIncome);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    @DisplayName("Validating a common income with constraints violations.")
    public void validateCommonIncomeTestCase2() {
        CommonIncome commonIncome = CommonIncome.create(1L, "", "", -20.0, null);
        List<String> expectedErrors = Arrays.asList(new String[] {
                "Name must be between 1 and 255 characters.",
                "Category must be present.",
                "Amount must be greater than 0."
        });
        IncomeValidator incomeValidator =  new IncomeValidator();
        List<String> errors = incomeValidator.validate(commonIncome);

        Assertions.assertEquals(expectedErrors, errors);
    }

    @Test
    @DisplayName("Validating a recurring income without constraints violations.")
    public void validateRecurringTestCase1() {
        RecurringIncome recurringIncome = RecurringIncome.create(1L, "Income Name", "Income Comment", 20.0, IncomeCategory.WAGE, 4);
        IncomeValidator incomeValidator = new IncomeValidator();

        List<String> errors = incomeValidator.validate(recurringIncome);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    @DisplayName("Validating a recurring income with constraints violations.")
    public void validateRecurringIncomeTestCase2() {
        RecurringIncome recurringIncome = RecurringIncome.create(1L, "", "", -20.0, null, 0);
        List<String> expectedErrors = Arrays.asList(new String[] {
                "Name must be between 1 and 255 characters.",
                "Category must be present.",
                "Amount must be greater than 0.",
                "Days occurrence must be greater than 0."
        });
        IncomeValidator incomeValidator = new IncomeValidator();
        List<String> errors = incomeValidator.validate(recurringIncome);

        Assertions.assertEquals(expectedErrors, errors);
    }
}

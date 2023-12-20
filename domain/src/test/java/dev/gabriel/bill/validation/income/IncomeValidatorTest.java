package dev.gabriel.bill.validation.income;

import dev.gabriel.bill.entities.income.RecurringIncome;
import dev.gabriel.bill.validation.income.IIncomeValidator;
import dev.gabriel.bill.validation.income.IncomeValidator;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.shared.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class IncomeValidatorTest {
    RecurringIncome populate() {
        return RecurringIncome.create(
                UUID.randomUUID().toString(),
                "", "", Money.create(BigDecimal.valueOf(-20), CurrencyType.BRL),
                null, 0, null, null);
    }

    @Test
    @DisplayName("Should validate name properly.")
    void validateNameTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateName(recurringIncome.getName());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate comment properly.")
    void validateCommentTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateComment(recurringIncome.getComment());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate amount properly.")
    void validateAmountTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateAmount(recurringIncome.getAmount());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate category properly.")
    void validateCategoryTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateCategory(recurringIncome.getCategory());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate status properly.")
    void validateStatusTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateStatus(recurringIncome.getStatus());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate payments date properly.")
    void validatePaymentsDateTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validatePaymentsDate(recurringIncome.getPreviousPaymentDate(), recurringIncome.getNextPaymentDate());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate days occurrence properly.")
    void validateDaysOccurrenceTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateDaysOccurrence(recurringIncome.getDaysOccurrence());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate user id properly.")
    void validateUserIdTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateUserId(recurringIncome.getUserId());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate cycles properly.")
    void validateCyclesTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateCycles(recurringIncome.getCycles());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate start date properly.")
    void validateStartDateTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        String error = incomeValidator.validateStartDate(recurringIncome.getStartDate());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate income properly.")
    void validateIncomeTestCase() {
        RecurringIncome recurringIncome = populate();
        IIncomeValidator incomeValidator = new IncomeValidator();
        List<String> errors = incomeValidator.validate(recurringIncome);

        Assertions.assertEquals(5, errors.size());
    }
}

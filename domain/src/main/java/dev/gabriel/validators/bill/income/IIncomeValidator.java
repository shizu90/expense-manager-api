package dev.gabriel.validators.bill.income;

import dev.gabriel.entities.bill.income.Income;
import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;

import java.time.LocalDate;
import java.util.List;

public interface IIncomeValidator {
    String validateName(String name);
    String validateComment(String comment);
    String validateAmount(Money amount);
    String validateCategory(IncomeCategory category);
    String validateStatus(BillStatus status);
    String validatePaymentsDate(LocalDate prev, LocalDate next);
    String validateUserId(Identity userId);
    String validateCycles(Long cycles);
    String validateStartDate(LocalDate startDate);
    String validateDaysOccurrence(Integer daysOccurrence);
    List<String> validate(Income income);
}

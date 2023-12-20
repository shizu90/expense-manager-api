package dev.gabriel.validators.bill.income;

import dev.gabriel.entities.bill.income.Income;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.valueobjects.Money;

import java.time.LocalDate;
import java.util.List;

public interface IIncomeValidator {
    String validateName(String name);
    String validateComment(String comment);
    String validateAmount(Money amount);
    String validateCategory(IncomeCategory category);
    String validatePaymentsDate(LocalDate prev, LocalDate next);
    String validateDaysOccurrence(Integer daysOccurrence);
    List<String> validate(Income income);
}

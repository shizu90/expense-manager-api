package dev.gabriel.bill.validation.income;

import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.entities.income.IncomeCategory;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

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

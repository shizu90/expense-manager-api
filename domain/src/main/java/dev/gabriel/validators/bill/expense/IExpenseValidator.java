package dev.gabriel.validators.bill.expense;

import dev.gabriel.entities.bill.expense.Expense;
import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.valueobjects.Money;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseValidator {
    String validateName(String name);
    String validateComment(String comment);
    String validateAmount(Money amount);
    String validateCategory(ExpenseCategory category);
    String validateStatus(BillStatus status);
    String validatePaymentsDate(LocalDate prev, LocalDate next);
    String validateDaysOccurrence(Integer daysOccurrence);
    List<String> validate(Expense expense);
}

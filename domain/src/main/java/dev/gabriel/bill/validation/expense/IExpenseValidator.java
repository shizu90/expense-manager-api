package dev.gabriel.bill.validation.expense;

import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.entities.expense.ExpenseCategory;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

import java.time.LocalDate;
import java.util.List;

public interface IExpenseValidator {
    String validateName(String name);
    String validateComment(String comment);
    String validateAmount(Money amount);
    String validateCategory(ExpenseCategory category);
    String validateStatus(BillStatus status);
    String validateUserId(Identity userId);
    String validateCycles(Long cycles);
    String validateStartDate(LocalDate startDate);
    String validatePaymentsDate(LocalDate prev, LocalDate next);
    String validateDaysOccurrence(Integer daysOccurrence);
    List<String> validate(Expense expense);
}

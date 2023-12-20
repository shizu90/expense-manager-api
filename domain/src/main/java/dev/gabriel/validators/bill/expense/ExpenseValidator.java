package dev.gabriel.validators.bill.expense;

import dev.gabriel.entities.bill.IRecurringBill;
import dev.gabriel.entities.bill.expense.Expense;
import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.validators.DomainValidator;
import dev.gabriel.valueobjects.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ExpenseValidator extends DomainValidator implements IExpenseValidator {
    @Override
    public String validateName(String name) {
        String errorLabel = "Name must be between 1 and 255 characters.";
        if(name.isEmpty() || name.length() > 255) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validateComment(String comment) {
        String errorLabel = "Comment must have less than 1510 characters.";
        if(comment.length() > 1510) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validateCategory(ExpenseCategory category) {
        String errorLabel = "Category must be present.";
        if(category == null) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validateAmount(Money amount) {
        String errorLabel = "Amount must be greater than 0.";
        BigDecimal value = (BigDecimal) amount.getAtomicValues().get(0);
        if(value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validatePaymentsDate(LocalDate previousPaymentDate, LocalDate nextPaymentDate) {
        String errorLabel = "Next payment date must be after previous payment date.";
        if(previousPaymentDate.isAfter(nextPaymentDate)) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validateDaysOccurrence(Integer daysOccurrence) {
        String errorLabel = "Days occurrence must be greater than 0.";
        if(daysOccurrence == null || daysOccurrence <= 0) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public List<String> validate(Expense expense) {
        validateName(expense.getName());
        validateComment(expense.getComment());
        validateAmount(expense.getAmount());
        validateCategory(expense.getCategory());
        if(expense instanceof IRecurringBill recurringBill) {
            validateDaysOccurrence(recurringBill.getDaysOccurrence());
            validatePaymentsDate((LocalDate) recurringBill.getPreviousPaymentDate().getAtomicValues().get(0), (LocalDate) recurringBill.getNextPaymentDate().getAtomicValues().get(0));
        }
        return errors;
    }
}

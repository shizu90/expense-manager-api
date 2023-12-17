package dev.gabriel.validators;

import dev.gabriel.entities.Expense;
import dev.gabriel.entities.IRecurringBill;

import java.util.ArrayList;
import java.util.List;

public class ExpenseValidator implements IValidator<Expense> {
    public List<String> validate(Expense expense) {
        List<String> errors = new ArrayList<>();

        if(expense.getName().isEmpty() || expense.getName().length() > 255) {
            errors.add("Name must be between 1 and 255 characters.");
        }
        if(expense.getComment().length() > 1510) {
            errors.add("Comment must have less than 1510 characters.");
        }
        if(expense.getCategory() == null) {
            errors.add("Category must be present.");
        }
        if(expense.getAmount() == null || expense.getAmount().getValue() <= 0) {
            errors.add("Amount must be greater than 0.");
        }
        if(expense.getCreatedAt() == null) {
            errors.add("Creation date must be present.");
        }

        if(expense instanceof IRecurringBill recurringBill) {
            if(recurringBill.getNextPaymentDate() == null) {
                errors.add("Next payment date must be present.");
            }
            if(recurringBill.getPreviousPaymentDate() == null) {
                errors.add("Previous payment date must be present.");
            }
            if(recurringBill.getDaysOccurrence() == null || recurringBill.getDaysOccurrence() <= 0) {
                errors.add("Days occurrence must be greater than 0.");
            }
            if(recurringBill.getPreviousPaymentDate().after(recurringBill.getNextPaymentDate())) {
                errors.add("Next payment date must be after previous payment date.");
            }
        }

        return errors;
    }
}

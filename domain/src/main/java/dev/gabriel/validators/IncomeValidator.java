package dev.gabriel.validators;

import dev.gabriel.entities.IRecurringBill;
import dev.gabriel.entities.Income;

import java.util.ArrayList;
import java.util.List;

public class IncomeValidator implements IValidator<Income> {
    public List<String> validate(Income income) {
        List<String> errors = new ArrayList<>();

        if(income.getName().isEmpty() || income.getName().length() > 255) {
            errors.add("Name must be between 1 and 255 characters.");
        }
        if(income.getComment().length() > 1510) {
            errors.add("Comment must have less than 1510 characters.");
        }
        if(income.getCategory() == null) {
            errors.add("Category must be present.");
        }
        if(income.getAmount() == null || income.getAmount().getValue() <= 0) {
            errors.add("Amount must be greater than 0.");
        }
        if(income.getCreatedAt() == null) {
            errors.add("Creation date must be present.");
        }

        if(income instanceof IRecurringBill recurringBill) {
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

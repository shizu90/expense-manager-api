package dev.gabriel.validations;

import dev.gabriel.entities.wallet.IRecurringBill;
import dev.gabriel.entities.wallet.incomes.Income;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.valueobjects.Money;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncomeValidator implements IValidator<Income> {
    private final List<String> validationErrors;

    public IncomeValidator() {
        validationErrors = new ArrayList<>();
    }

    public String validateName(String name) {
        String errorLabel = "Name must be between 1 and 255 characters.";
        if(name.isEmpty() || name.length() > 255) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validateComment(String comment) {
        String errorLabel = "Comment must have less than 1510 characters.";
        if(comment.length() > 1510) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validateCategory(IncomeCategory category) {
        String errorLabel = "Category must be present.";
        if(category == null) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validateAmount(Money amount) {
        String errorLabel = "Amount must be greater than 0.";
        if(amount == null || amount.getValue() < 0) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validateNextPaymentDate(Date previousPaymentDate, Date nextPaymentDate) {
        String errorLabel = "Next payment date must be after previous payment date.";
        if(previousPaymentDate.after(nextPaymentDate)) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validateDaysOccurrence(Integer daysOccurrence) {
        String errorLabel = "Days occurrence must be greater than 0.";
        if(daysOccurrence == null || daysOccurrence <= 0) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public List<String> validate(Income income) {
        validateName(income.getName());
        validateComment(income.getComment());
        validateAmount(income.getAmount());
        validateCategory(income.getCategory());

        if(income instanceof IRecurringBill recurringBill) {
            validateNextPaymentDate(recurringBill.getPreviousPaymentDate(), recurringBill.getNextPaymentDate());
            validateDaysOccurrence(recurringBill.getDaysOccurrence());
        }

        return validationErrors;
    }
}

package dev.gabriel.bill.validation.expense;

import dev.gabriel.bill.entities.IRecurringBill;
import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.entities.expense.ExpenseCategory;
import dev.gabriel.shared.validation.DomainValidator;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

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
    public String validateStatus(BillStatus status) {
        String errorLabel = "Status must be present.";
        if(status == null) {
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
    public String validateUserId(Identity userId) {
        String errorLabel = "An user must be present.";
        if(userId == null) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    @Override
    public String validateCycles(Long cycles) {
        String errorLabel = "Invalid cycles count.";
        if(cycles == null || cycles < 0) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    @Override
    public String validateStartDate(LocalDate startDate) {
        String errorLabel = "Start date must be present.";
        if(startDate == null) {
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
        validateUserId(expense.getUserId());
        if(expense instanceof IRecurringBill recurringBill) {
            validateDaysOccurrence(recurringBill.getDaysOccurrence());
            validatePaymentsDate(recurringBill.getPreviousPaymentDate(), recurringBill.getNextPaymentDate());
            validateCycles(recurringBill.getCycles());
            validateStartDate(recurringBill.getStartDate());
        }
        return errors;
    }
}

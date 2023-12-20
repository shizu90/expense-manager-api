package dev.gabriel.bill.validation.income;

import dev.gabriel.bill.entities.IRecurringBill;
import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.entities.income.IncomeCategory;
import dev.gabriel.shared.validation.DomainValidator;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class IncomeValidator extends DomainValidator implements IIncomeValidator {
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
    public String validateCategory(IncomeCategory category) {
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
    public String validateStatus(BillStatus status) {
        String errorLabel = "Status must be present.";
        if(status == null) {
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
    public List<String> validate(Income income) {
        validateName(income.getName());
        validateComment(income.getComment());
        validateAmount(income.getAmount());
        validateCategory(income.getCategory());
        validateUserId(income.getUserId());
        if(income instanceof IRecurringBill recurringBill) {
            validateDaysOccurrence(recurringBill.getDaysOccurrence());
            validatePaymentsDate((LocalDate) recurringBill.getPreviousPaymentDate().getAtomicValues().get(0), (LocalDate) recurringBill.getNextPaymentDate().getAtomicValues().get(0));
            validateCycles(recurringBill.getCycles());
            validateStartDate((LocalDate) recurringBill.getStartDate().getAtomicValues().get(0));
        }
        return errors;
    }
}

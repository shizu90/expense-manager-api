package dev.gabriel.validators.transaction;

import dev.gabriel.entities.transaction.Transaction;
import dev.gabriel.validators.DomainValidator;
import dev.gabriel.valueobjects.Money;

import java.math.BigDecimal;
import java.util.List;

public class TransactionValidator extends DomainValidator implements ITransactionValidator {
    @Override
    public String validateTotalPaid(Money totalPaid) {
        String errorLabel = "Transaction total paid must not be smaller than 0.";
        BigDecimal value = (BigDecimal) totalPaid.getAtomicValues().get(0);
        if(value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    @Override
    public List<String> validate(Transaction transaction) {
        validateTotalPaid(transaction.getTotalPaid());
        return errors;
    }
}

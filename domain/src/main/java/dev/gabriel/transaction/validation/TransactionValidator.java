package dev.gabriel.transaction.validation;

import dev.gabriel.shared.validation.DomainValidator;
import dev.gabriel.transaction.entities.Transaction;
import dev.gabriel.transaction.entities.TransactionType;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

import java.math.BigDecimal;
import java.util.List;

public class TransactionValidator extends DomainValidator implements ITransactionValidator {
    @Override
    public String validateBillId(Identity billId) {
        String errorLabel = "Bill id must be present.";
        if(billId == null) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validateWalletId(Identity walletId) {
        String errorLabel = "Wallet id must be present.";
        if(walletId == null) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

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
    public String validateTransactionType(TransactionType transactionType) {
        String errorLabel = "Transaction type must be present.";
        if(transactionType == null) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    @Override
    public List<String> validate(Transaction transaction) {
        validateTotalPaid(transaction.getTotalPaid());
        validateTransactionType(transaction.getTransactionType());
        validateBillId(transaction.getBillId());
        validateWalletId(transaction.getWalletId());
        return errors;
    }
}

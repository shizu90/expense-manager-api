package dev.gabriel.validators.wallet;

import dev.gabriel.entities.wallet.Wallet;
import dev.gabriel.enums.CurrencyType;
import dev.gabriel.validators.DomainValidator;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class WalletValidator extends DomainValidator implements IWalletValidator {
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
        if(comment.isEmpty() || comment.length() > 1510) {
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
    public String validateLastBalanceUpdate(LocalDate lastBalanceUpdate) {
        String errorLabel = "Last balance update must be present.";
        if(lastBalanceUpdate == null) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validateInitialBalance(Money initialBalance) {
        String errorLabel = "Initial balance must not be smaller than 0.";
        if(initialBalance == null || !initialBalance.isGreater(Money.create(BigDecimal.ZERO, CurrencyType.BRL))) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    @Override
    public List<String> validate(Wallet wallet) {
        validateName(wallet.getName());
        validateComment(wallet.getComment());
        validateInitialBalance(wallet.getInitialBalance());
        validateLastBalanceUpdate(wallet.getLastBalanceUpdate());
        validateUserId(wallet.getUserId());
        return errors;
    }
}

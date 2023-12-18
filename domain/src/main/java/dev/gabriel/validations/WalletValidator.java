package dev.gabriel.validations;

import dev.gabriel.entities.wallet.Wallet;
import dev.gabriel.valueobjects.Money;

import java.util.ArrayList;
import java.util.List;

public class WalletValidator implements IValidator<Wallet> {
    private final List<String> validationErrors;

    public WalletValidator() {
        validationErrors = new ArrayList<>();
    }

    public String validateName(String name) {
        String errorLabel = "Name must be between 1 and 255 characters.";
        if(name.isEmpty() || name.length() > 255) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validateBalance(Money balance) {
        String errorLabel = "Balance must be present.";
        if(balance == null) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public List<String> validate(Wallet wallet) {
        validateName(wallet.getName());
        validateBalance(wallet.getBalance());

        return validationErrors;
    }
}

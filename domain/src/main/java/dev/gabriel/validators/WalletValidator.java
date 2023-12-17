package dev.gabriel.validators;

import dev.gabriel.entities.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletValidator implements IValidator<Wallet> {
    public List<String> validate(Wallet wallet) {
        List<String> errors = new ArrayList<>();

        if(wallet.getName().isEmpty() || wallet.getName().length() > 255) {
            errors.add("Name must be between 1 and 255 characters.");
        }
        if(wallet.getBalance() == null || wallet.getBalance().getValue() < 0) {
            errors.add("Balance must be present.");
        }

        return errors;
    }
}

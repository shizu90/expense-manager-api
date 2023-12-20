package dev.gabriel.validators.wallet;

import dev.gabriel.entities.wallet.Wallet;
import dev.gabriel.validators.DomainValidator;

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
    public List<String> validate(Wallet wallet) {
        validateName(wallet.getName());
        validateComment(wallet.getComment());
        return errors;
    }
}

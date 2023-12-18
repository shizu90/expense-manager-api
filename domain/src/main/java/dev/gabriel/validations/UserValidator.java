package dev.gabriel.validations;

import dev.gabriel.entities.user.User;
import dev.gabriel.entities.wallet.Wallet;

import java.util.ArrayList;
import java.util.List;

public class UserValidator implements IValidator<User> {
    private final List<String> validationErrors;

    public UserValidator() {
        validationErrors = new ArrayList<>();
    }

    public String validateName(String name) {
        String errorLabel = "Name must be between 3 and 128 characters.";
        if(name == null || name.length() < 3 || name.length() > 128) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validateEmail(String email) {
        String errorLabel = "Email is not a valid address.";
        if(email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validatePassword(String passwd) {
        String errorLabel = "Password must be between 6 and 24 characters.";
        if(passwd == null || passwd.length() < 6 || passwd.length() > 24) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public String validateWallets(List<Wallet> wallets) {
        String errorLabel = "User must have a wallet and not more than 4.";
        if(wallets.isEmpty() || wallets.size() > 4) {
            validationErrors.add(errorLabel);
            return errorLabel;
        }else return null;
    }

    public List<String> validate(User user) {
        validateName(user.getConfiguration().getName());
        validateEmail(user.getConfiguration().getEmail());
        validatePassword(user.getConfiguration().getPasswd());
        validateWallets(user.getWallets());

        return validationErrors;
    }
}

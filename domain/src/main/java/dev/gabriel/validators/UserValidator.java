package dev.gabriel.validators;

import dev.gabriel.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserValidator implements IValidator<User> {
    public List<String> validate(User user) {
        List<String> errors = new ArrayList<>();

        if(user.getConfiguration().getName() == null || user.getConfiguration().getName().length() < 3 || user.getConfiguration().getName().length() > 128) {
            errors.add("Name must be between 3 and 128 characters.");
        }
        if(user.getConfiguration().getEmail() == null || !user.getConfiguration().getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.add("Email is not a valid address.");
        }
        if(user.getConfiguration().getPasswd() == null || user.getConfiguration().getPasswd().length() < 6 || user.getConfiguration().getPasswd().length() > 24) {
            errors.add("Password must be between 6 and 24 characters.");
        }
        if(user.getConfiguration().getCreatedAt() == null) {
            errors.add("Creation date must be present.");
        }
        if(user.getWallets().size() == 0 || user.getWallets().size() > 4) {
            errors.add("User must have a wallet and not more than 4.");
        }

        return errors;
    }
}

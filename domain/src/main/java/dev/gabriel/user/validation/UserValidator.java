package dev.gabriel.user.validation;

import dev.gabriel.shared.validation.DomainValidator;
import dev.gabriel.user.entities.User;

import java.util.List;

public class UserValidator extends DomainValidator implements IUserValidator {
    @Override
    public String validateName(String name) {
        String errorLabel = "Name must be between 3 and 128 characters.";
        if(name == null || name.length() < 3 || name.length() > 128) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validateEmail(String email) {
        String errorLabel = "Email is not a valid address.";
        if(email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public String validatePassword(String passwd) {
        String errorLabel = "Password must be between 6 and 24 characters.";
        if(passwd == null || passwd.length() < 6 || passwd.length() > 24) {
            errors.add(errorLabel);
            return errorLabel;
        }else return null;
    }
    @Override
    public List<String> validate(User user) {
        validateName(user.getName());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        return errors;
    }
}

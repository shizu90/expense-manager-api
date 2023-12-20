package dev.gabriel.user.validation;

import dev.gabriel.user.entities.User;

import java.util.List;

public interface IUserValidator {
    String validateName(String name);
    String validateEmail(String email);
    String validatePassword(String password);
    List<String> validate(User user);
}

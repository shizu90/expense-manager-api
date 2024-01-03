package dev.gabriel.user.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

@Getter
public class CreateUserCommand implements ICommand {
    private String email;
    private String name;
    private String password;
    private String defaultCurrencyCode;
    private String defaultLanguage;
}

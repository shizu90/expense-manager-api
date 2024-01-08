package dev.gabriel.user.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserCommand extends Command {
    private String email;
    private String name;
    private String password;
    private String defaultCurrencyCode;
    private String defaultLanguage;
}

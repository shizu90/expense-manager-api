package dev.gabriel.user.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeUserDefaultCurrencyCodeCommand implements ICommand {
    private String userId;
    private String currencyCode;
}

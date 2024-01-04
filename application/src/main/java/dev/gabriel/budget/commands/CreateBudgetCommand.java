package dev.gabriel.budget.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBudgetCommand implements ICommand {
    private String name;
    private String comment;
    private String userId;
    private String currencyCode;
}

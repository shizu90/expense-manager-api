package dev.gabriel.budget.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBudgetCommand extends Command {
    private String name;
    private String comment;
    private String userId;
    private String currencyCode;
}

package dev.gabriel.budget.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditBudgetDescriptionCommand implements ICommand {
    private String budgetId;
    private String description;
}

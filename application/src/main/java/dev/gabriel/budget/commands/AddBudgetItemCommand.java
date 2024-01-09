package dev.gabriel.budget.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AddBudgetItemCommand extends Command {
    private UUID budgetId;
    private UUID billId;
}

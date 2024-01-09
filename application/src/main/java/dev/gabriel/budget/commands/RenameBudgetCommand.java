package dev.gabriel.budget.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class RenameBudgetCommand extends Command {
    private UUID budgetId;
    private String name;
}

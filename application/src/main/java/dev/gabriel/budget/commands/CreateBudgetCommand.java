package dev.gabriel.budget.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateBudgetCommand extends Command {
    private String name;
    private String comment;
    private UUID userId;
    private String currencyCode;
}

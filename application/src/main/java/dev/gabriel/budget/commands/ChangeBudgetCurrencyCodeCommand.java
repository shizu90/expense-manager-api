package dev.gabriel.budget.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeBudgetCurrencyCodeCommand extends Command {
    private String budgetId;
    private String currencyCode;
}

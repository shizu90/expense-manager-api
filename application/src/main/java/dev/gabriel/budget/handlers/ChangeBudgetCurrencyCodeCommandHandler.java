package dev.gabriel.budget.handlers;

import dev.gabriel.budget.commands.ChangeBudgetCurrencyCodeCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;

public class ChangeBudgetCurrencyCodeCommandHandler implements ICommandHandler<Budget, ChangeBudgetCurrencyCodeCommand> {
    private final IBudgetRepository budgetRepository;

    public ChangeBudgetCurrencyCodeCommandHandler(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget execute(ChangeBudgetCurrencyCodeCommand command) {
        Budget budget = budgetRepository
                .findById(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        budget.changeCurrencyCode(CurrencyCode.getConstant(command.getCurrencyCode()));

        return budgetRepository.save(budget);
    }
}

package dev.gabriel.budget.handlers;

import dev.gabriel.budget.commands.RenameBudgetCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class RenameBudgetCommandHandler implements ICommandHandler<Budget, RenameBudgetCommand> {
    private final IBudgetRepository budgetRepository;

    public RenameBudgetCommandHandler(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget execute(RenameBudgetCommand command) {
        Budget budget = budgetRepository
                .findById(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        budget.rename(command.getName());

        return budgetRepository.save(budget);
    }
}

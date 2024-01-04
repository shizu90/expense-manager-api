package dev.gabriel.budget.handlers;

import dev.gabriel.budget.commands.DeleteBudgetCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class DeleteBudgetCommandHandler implements ICommandHandler<Budget, DeleteBudgetCommand> {
    private final IBudgetRepository budgetRepository;

    public DeleteBudgetCommandHandler(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget execute(DeleteBudgetCommand command) {
        Budget budget = budgetRepository
                .findById(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        budget.delete();

        return budgetRepository.save(budget);
    }
}

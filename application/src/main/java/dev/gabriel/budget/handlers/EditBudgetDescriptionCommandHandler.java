package dev.gabriel.budget.handlers;

import dev.gabriel.budget.commands.EditBudgetDescriptionCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class EditBudgetDescriptionCommandHandler implements ICommandHandler<Budget, EditBudgetDescriptionCommand> {
    private final IBudgetRepository budgetRepository;

    public EditBudgetDescriptionCommandHandler(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget execute(EditBudgetDescriptionCommand command) {
        Budget budget = budgetRepository
                .findById(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        budget.editDescription(command.getDescription());

        return budgetRepository.save(budget);
    }
}

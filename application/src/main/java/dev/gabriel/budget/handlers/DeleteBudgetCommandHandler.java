package dev.gabriel.budget.handlers;

import dev.gabriel.budget.commands.DeleteBudgetCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteBudgetCommandHandler implements ICommandHandler<Budget, DeleteBudgetCommand> {
    private final IBudgetRepository budgetRepository;

    @Autowired
    public DeleteBudgetCommandHandler(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget handle(DeleteBudgetCommand command) {
        Budget budget = budgetRepository
                .load(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        budget.delete();

        return budgetRepository.registerEvents(budget);
    }

    @Override
    public Class<DeleteBudgetCommand> getCommandType() {
        return DeleteBudgetCommand.class;
    }
}

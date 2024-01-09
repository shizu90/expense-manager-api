package dev.gabriel.budget.handlers;

import dev.gabriel.budget.commands.RenameBudgetCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenameBudgetCommandHandler implements ICommandHandler<Budget, RenameBudgetCommand> {
    private final IBudgetRepository budgetRepository;

    @Autowired
    public RenameBudgetCommandHandler(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget handle(RenameBudgetCommand command) {
        Budget budget = budgetRepository
                .load(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        budget.rename(command.getName());

        return budgetRepository.registerEvents(budget);
    }

    @Override
    public Class<RenameBudgetCommand> getCommandType() {
        return RenameBudgetCommand.class;
    }
}

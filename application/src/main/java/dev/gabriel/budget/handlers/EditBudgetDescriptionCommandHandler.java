package dev.gabriel.budget.handlers;

import dev.gabriel.budget.commands.EditBudgetDescriptionCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditBudgetDescriptionCommandHandler implements ICommandHandler<Budget, EditBudgetDescriptionCommand> {
    private final IBudgetRepository budgetRepository;

    @Autowired
    public EditBudgetDescriptionCommandHandler(IBudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget handle(EditBudgetDescriptionCommand command) {
        Budget budget = budgetRepository
                .load(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        budget.editDescription(command.getDescription());

        return budgetRepository.registerEvents(budget);
    }

    @Override
    public Class<EditBudgetDescriptionCommand> getCommandType() {
        return EditBudgetDescriptionCommand.class;
    }
}

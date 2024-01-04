package dev.gabriel.budget.handlers;

import dev.gabriel.budget.commands.CreateBudgetCommand;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.UUID;

public class CreateBudgetCommandHandler implements ICommandHandler<Budget, CreateBudgetCommand> {
    private final IBudgetRepository budgetRepository;
    private final IUserRepository userRepository;

    public CreateBudgetCommandHandler(IBudgetRepository budgetRepository, IUserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Budget execute(CreateBudgetCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        Budget budget = Budget.create(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getComment(),
                CurrencyCode.valueOf(command.getCurrencyCode()),
                user.getId()
        );

        return budgetRepository.save(budget);
    }
}
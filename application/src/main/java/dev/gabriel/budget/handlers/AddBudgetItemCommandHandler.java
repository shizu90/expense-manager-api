package dev.gabriel.budget.handlers;

import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.budget.commands.AddBudgetItemCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class AddBudgetItemCommandHandler implements ICommandHandler<Budget, AddBudgetItemCommand> {
    private final IBudgetRepository budgetRepository;
    private final IBillRepository billRepository;

    public AddBudgetItemCommandHandler(IBudgetRepository budgetRepository, IBillRepository billRepository) {
        this.budgetRepository = budgetRepository;
        this.billRepository = billRepository;
    }

    @Override
    public Budget execute(AddBudgetItemCommand command) {
        Budget budget = budgetRepository
                .findById(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        Bill bill = billRepository
                .findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        budget.addBill(bill);

        return budgetRepository.save(budget);
    }
}

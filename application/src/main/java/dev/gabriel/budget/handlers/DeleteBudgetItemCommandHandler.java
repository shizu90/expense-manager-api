package dev.gabriel.budget.handlers;

import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.budget.commands.DeleteBudgetItemCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class DeleteBudgetItemCommandHandler implements ICommandHandler<Budget, DeleteBudgetItemCommand> {
    private final IBudgetRepository budgetRepository;
    private final IBillRepository billRepository;

    public DeleteBudgetItemCommandHandler(IBudgetRepository budgetRepository, IBillRepository billRepository) {
        this.budgetRepository = budgetRepository;
        this.billRepository = billRepository;
    }

    @Override
    public Budget execute(DeleteBudgetItemCommand command) {
        Budget budget = budgetRepository
                .findById(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        Bill bill = billRepository
                .findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        budget.deleteBill(bill);

        return budgetRepository.save(budget);
    }
}

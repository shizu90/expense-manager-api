package dev.gabriel.budget.handlers;

import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.budget.commands.DeleteBudgetItemCommand;
import dev.gabriel.budget.exceptions.BudgetNotFoundException;
import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.services.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class DeleteBudgetItemCommandHandler implements ICommandHandler<Budget, DeleteBudgetItemCommand> {
    private final IBudgetRepository budgetRepository;
    private final IBillRepository billRepository;
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public DeleteBudgetItemCommandHandler(IBudgetRepository budgetRepository,
                                          IBillRepository billRepository,
                                          CurrencyConversionService currencyConversionService
    ) {
        this.budgetRepository = budgetRepository;
        this.billRepository = billRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    public Budget handle(DeleteBudgetItemCommand command) {
        Budget budget = budgetRepository
                .findById(BudgetId.create(command.getBudgetId())).orElseThrow(() -> new BudgetNotFoundException(command.getBudgetId()));
        Bill bill = billRepository
                .findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));

        if(!bill.getAmount().getCurrencyCode().equals(budget.getTotalAmount().getCurrencyCode())) {
            BigDecimal amountToDiscount = currencyConversionService.convert(bill.getAmount(), budget.getTotalAmount().getCurrencyCode());
            bill.changeAmount(amountToDiscount);
        }

        BigDecimal budgetAmount = budget.getTotalAmount().getValue();
        BigDecimal amountToDiscount = bill.getAmount().getValue();

        if(!bill.getAmount().getCurrencyCode().equals(budget.getTotalAmount().getCurrencyCode())) {
            amountToDiscount = currencyConversionService.convert(bill.getAmount(), budget.getTotalAmount().getCurrencyCode());
        }

        if(bill.getType().equals(BillType.EXPENSE)) {
            budgetAmount = budgetAmount.subtract(amountToDiscount);
        }else {
            budgetAmount = budgetAmount.add(amountToDiscount);
        }

        budget.deleteBill(bill);
        budget.updateTotalAmount(budgetAmount);

        return budgetRepository.save(budget);
    }
}

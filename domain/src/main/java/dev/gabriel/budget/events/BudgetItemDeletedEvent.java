package dev.gabriel.budget.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.valueobjects.Currency;
import lombok.Getter;

@Getter
public class BudgetItemDeletedEvent extends BudgetEvent {
    private final BillId billId;
    private final Currency amount;

    public BudgetItemDeletedEvent(BudgetId budgetId, Long version, BillId billId, Currency amount) {
        super(budgetId, version);
        this.billId = billId;
        this.amount = amount;
    }
}

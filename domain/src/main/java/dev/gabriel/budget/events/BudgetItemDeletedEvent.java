package dev.gabriel.budget.events;

import lombok.Getter;

@Getter
public class BudgetItemDeletedEvent extends BudgetEvent {
    private final String billId;

    public BudgetItemDeletedEvent(String budgetId, Long version, String billId) {
        super(budgetId, version);
        this.billId = billId;
    }
}

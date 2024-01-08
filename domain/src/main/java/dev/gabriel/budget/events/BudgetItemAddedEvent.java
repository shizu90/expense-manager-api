package dev.gabriel.budget.events;

import lombok.Getter;

@Getter
public class BudgetItemAddedEvent extends BudgetEvent {
    private final String billId;

    public BudgetItemAddedEvent(String budgetId, Long version, String billId) {
        super(budgetId, version);
        this.billId = billId;
    }
}

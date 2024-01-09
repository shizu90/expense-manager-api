package dev.gabriel.budget.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BudgetItemAddedEvent extends BudgetEvent {
    private final UUID billId;

    public BudgetItemAddedEvent(UUID budgetId, Long version, UUID billId) {
        super(budgetId, version);
        this.billId = billId;
    }
}

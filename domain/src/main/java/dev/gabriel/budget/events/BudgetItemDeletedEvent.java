package dev.gabriel.budget.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BudgetItemDeletedEvent extends BudgetEvent {
    private final UUID billId;

    public BudgetItemDeletedEvent(UUID budgetId, Long version, UUID billId) {
        super(budgetId, version);
        this.billId = billId;
    }
}

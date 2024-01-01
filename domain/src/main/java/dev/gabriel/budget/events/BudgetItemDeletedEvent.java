package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BudgetItemDeletedEvent extends DomainEvent {
    public BudgetItemDeletedEvent(BudgetId budgetId) {
        super(budgetId, Instant.now());
    }
}

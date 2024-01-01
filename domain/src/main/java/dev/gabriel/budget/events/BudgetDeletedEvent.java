package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BudgetDeletedEvent extends DomainEvent {
    public BudgetDeletedEvent(BudgetId budgetId) {
        super(budgetId, Instant.now());
    }
}

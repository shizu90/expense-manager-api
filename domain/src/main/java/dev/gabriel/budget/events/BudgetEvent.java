package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BudgetEvent extends DomainEvent {
    protected BudgetEvent(BudgetId budgetId) {
        super(budgetId, Instant.now());
    }
}
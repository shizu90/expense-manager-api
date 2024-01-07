package dev.gabriel.budget.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BudgetEvent extends DomainEvent {
    protected BudgetEvent(String budgetId, Long version) {
        super(budgetId, version, Instant.now());
    }
}

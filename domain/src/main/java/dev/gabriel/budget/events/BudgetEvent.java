package dev.gabriel.budget.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class BudgetEvent extends DomainEvent {
    protected BudgetEvent(UUID budgetId, Long version) {
        super(budgetId, version, Instant.now());
    }
}

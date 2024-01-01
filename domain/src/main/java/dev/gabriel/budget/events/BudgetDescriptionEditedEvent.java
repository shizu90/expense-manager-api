package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BudgetDescriptionEditedEvent extends DomainEvent {
    public BudgetDescriptionEditedEvent(BudgetId budgetId) {
        super(budgetId, Instant.now());
    }
}

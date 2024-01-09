package dev.gabriel.budget.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BudgetDescriptionEditedEvent extends BudgetEvent {
    private final String description;

    public BudgetDescriptionEditedEvent(UUID budgetId, Long version, String description) {
        super(budgetId, version);
        this.description = description;
    }
}

package dev.gabriel.budget.events;

import lombok.Getter;

@Getter
public class BudgetDescriptionEditedEvent extends BudgetEvent {
    private final String description;

    public BudgetDescriptionEditedEvent(String budgetId, Long version, String description) {
        super(budgetId, version);
        this.description = description;
    }
}

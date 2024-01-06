package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetDescription;
import dev.gabriel.budget.valueobjects.BudgetId;
import lombok.Getter;

@Getter
public class BudgetDescriptionEditedEvent extends BudgetEvent {
    private final BudgetDescription description;

    public BudgetDescriptionEditedEvent(BudgetId budgetId, Long version, BudgetDescription description) {
        super(budgetId, version);
        this.description = description;
    }
}

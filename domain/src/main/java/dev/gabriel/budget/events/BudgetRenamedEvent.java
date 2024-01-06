package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.budget.valueobjects.BudgetName;
import lombok.Getter;

@Getter
public class BudgetRenamedEvent extends BudgetEvent {
    private final BudgetName name;

    public BudgetRenamedEvent(BudgetId budgetId, Long version, BudgetName name) {
        super(budgetId, version);
        this.name = name;
    }
}

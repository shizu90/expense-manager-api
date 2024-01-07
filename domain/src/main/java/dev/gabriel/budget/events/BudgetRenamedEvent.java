package dev.gabriel.budget.events;

import lombok.Getter;

@Getter
public class BudgetRenamedEvent extends BudgetEvent {
    private final String name;

    public BudgetRenamedEvent(String budgetId, Long version, String name) {
        super(budgetId, version);
        this.name = name;
    }
}

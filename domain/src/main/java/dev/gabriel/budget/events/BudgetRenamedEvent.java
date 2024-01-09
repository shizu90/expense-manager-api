package dev.gabriel.budget.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BudgetRenamedEvent extends BudgetEvent {
    private final String name;

    public BudgetRenamedEvent(UUID budgetId, Long version, String name) {
        super(budgetId, version);
        this.name = name;
    }
}

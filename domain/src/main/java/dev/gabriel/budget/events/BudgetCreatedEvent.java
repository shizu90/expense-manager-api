package dev.gabriel.budget.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

@Getter
public class BudgetCreatedEvent extends BudgetEvent {
    private final String name;
    private final String description;
    private final String userId;
    private final CurrencyCode currencyCode;

    public BudgetCreatedEvent(String budgetId, Long version, String name, String description, String userId, CurrencyCode currencyCode) {
        super(budgetId, version);
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.currencyCode = currencyCode;
    }
}

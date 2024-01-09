package dev.gabriel.budget.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

import java.util.UUID;

@Getter
public class BudgetCreatedEvent extends BudgetEvent {
    private final String name;
    private final String description;
    private final UUID userId;
    private final CurrencyCode currencyCode;

    public BudgetCreatedEvent(UUID budgetId, Long version, String name, String description, UUID userId, CurrencyCode currencyCode) {
        super(budgetId, version);
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.currencyCode = currencyCode;
    }
}

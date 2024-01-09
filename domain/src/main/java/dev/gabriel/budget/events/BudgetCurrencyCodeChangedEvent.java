package dev.gabriel.budget.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

import java.util.UUID;

@Getter
public class BudgetCurrencyCodeChangedEvent extends BudgetEvent {
    private final CurrencyCode currencyCode;

    public BudgetCurrencyCodeChangedEvent(UUID budgetId, Long version, CurrencyCode currencyCode) {
        super(budgetId, version);
        this.currencyCode = currencyCode;
    }
}

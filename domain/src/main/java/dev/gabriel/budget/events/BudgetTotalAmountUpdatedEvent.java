package dev.gabriel.budget.events;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class BudgetTotalAmountUpdatedEvent extends BudgetEvent {
    private final BigDecimal amount;

    public BudgetTotalAmountUpdatedEvent(UUID budgetId, Long version, BigDecimal amount) {
        super(budgetId, version);
        this.amount = amount;
    }
}

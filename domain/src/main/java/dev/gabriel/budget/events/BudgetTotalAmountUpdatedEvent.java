package dev.gabriel.budget.events;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BudgetTotalAmountUpdatedEvent extends BudgetEvent {
    private final BigDecimal amount;

    public BudgetTotalAmountUpdatedEvent(String budgetId, Long version, BigDecimal amount) {
        super(budgetId, version);
        this.amount = amount;
    }
}

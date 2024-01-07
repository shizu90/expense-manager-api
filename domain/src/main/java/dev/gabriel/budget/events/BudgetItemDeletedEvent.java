package dev.gabriel.budget.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BudgetItemDeletedEvent extends BudgetEvent {
    private final String billId;
    private final BigDecimal amount;
    private final CurrencyCode currencyCode;

    public BudgetItemDeletedEvent(String budgetId, Long version, String billId, BigDecimal amount, CurrencyCode currencyCode) {
        super(budgetId, version);
        this.billId = billId;
        this.amount = amount;
        this.currencyCode = currencyCode;
    }
}

package dev.gabriel.budget.events;

import dev.gabriel.budget.valueobjects.BudgetDescription;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.budget.valueobjects.BudgetName;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

@Getter
public class BudgetCreatedEvent extends BudgetEvent {
    private final BudgetName name;
    private final BudgetDescription description;
    private final UserId userId;
    private final Currency totalAmount;

    public BudgetCreatedEvent(BudgetId budgetId, Long version, BudgetName name, BudgetDescription description, UserId userId, Currency totalAmount) {
        super(budgetId, version);
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.totalAmount = totalAmount;
    }
}

package dev.gabriel.bill.events.expense;

import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.Identity;

public class ExpenseRemovedEvent extends DomainEvent {
    public ExpenseRemovedEvent(Identity expenseId) {
        super(expenseId);
    }
}

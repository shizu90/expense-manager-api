package dev.gabriel.bill.events.expense;

import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.Identity;

import java.time.Instant;

public class ExpenseCreatedEvent extends DomainEvent {
    public ExpenseCreatedEvent(Identity expenseId) {
        super(expenseId);
    }
}

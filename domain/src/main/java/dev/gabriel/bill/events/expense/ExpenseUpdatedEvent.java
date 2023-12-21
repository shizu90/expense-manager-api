package dev.gabriel.bill.events.expense;

import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.shared.events.DomainEvent;

public class ExpenseUpdatedEvent extends DomainEvent<Expense> {
    public ExpenseUpdatedEvent(Expense expense) {
        super(expense);
    }
}

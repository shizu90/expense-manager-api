package dev.gabriel.bill.events.expense;

import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.shared.events.DomainEvent;

public class ExpenseCreatedEvent extends DomainEvent<Expense> {
    public ExpenseCreatedEvent() {

    }
}

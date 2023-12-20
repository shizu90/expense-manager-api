package dev.gabriel.events.bill.expense;

import dev.gabriel.entities.bill.expense.Expense;
import dev.gabriel.events.DomainEvent;

public class ExpenseCreatedEvent extends DomainEvent<Expense> {
    public ExpenseCreatedEvent() {

    }
}

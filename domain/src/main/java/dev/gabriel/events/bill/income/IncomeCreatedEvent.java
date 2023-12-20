package dev.gabriel.events.bill.income;

import dev.gabriel.entities.bill.income.Income;
import dev.gabriel.events.DomainEvent;

public class IncomeCreatedEvent extends DomainEvent<Income> {
    public IncomeCreatedEvent() {

    }
}

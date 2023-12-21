package dev.gabriel.bill.events.income;

import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.shared.events.DomainEvent;

public class IncomeCreatedEvent extends DomainEvent<Income> {
    public IncomeCreatedEvent(Income income) {
        super(income);
    }
}
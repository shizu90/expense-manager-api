package dev.gabriel.bill.events.income;

import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.shared.events.DomainEvent;

public class IncomeUpdatedEvent extends DomainEvent<Income> {
    public IncomeUpdatedEvent(Income income) {
        super(income);
    }
}

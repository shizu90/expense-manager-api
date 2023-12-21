package dev.gabriel.bill.events.income;

import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.shared.events.DomainEvent;

public class IncomeRemovedEvent extends DomainEvent<Income> {
    public IncomeRemovedEvent(Income income) {
        super(income);
    }
}

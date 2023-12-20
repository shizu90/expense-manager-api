package dev.gabriel.bill.events.income;

import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.Identity;

public class IncomeUpdatedEvent extends DomainEvent {
    public IncomeUpdatedEvent(Identity incomeId) {
        super(incomeId);
    }
}

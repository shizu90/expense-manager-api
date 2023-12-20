package dev.gabriel.bill.events.income;

import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.Identity;

public class IncomeRemovedEvent extends DomainEvent {
    public IncomeRemovedEvent(Identity incomeId) {
        super(incomeId);
    }
}

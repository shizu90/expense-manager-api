package dev.gabriel.wallet.events;

import dev.gabriel.bill.valueobjects.IncomeId;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.wallet.entities.Wallet;
import lombok.Getter;

@Getter
public class IncomeReceivedEvent extends DomainEvent<Wallet> {
    private final IncomeId incomeId;

    public IncomeReceivedEvent(Wallet wallet, IncomeId incomeId) {
        super(wallet);
        this.incomeId = incomeId;
    }
}

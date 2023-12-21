package dev.gabriel.wallet.events;

import dev.gabriel.bill.valueobjects.ExpenseId;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.wallet.entities.Wallet;
import lombok.Getter;

@Getter
public class ExpensePaidEvent extends DomainEvent<Wallet> {
    private final ExpenseId expenseId;
    public ExpensePaidEvent(Wallet wallet, ExpenseId expenseId) {
        super(wallet);
        this.expenseId = expenseId;
    }
}

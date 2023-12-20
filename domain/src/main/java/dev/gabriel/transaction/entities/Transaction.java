package dev.gabriel.transaction.entities;

import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import dev.gabriel.transaction.events.TransactionCreatedEvent;
import dev.gabriel.transaction.events.TransactionRemovedEvent;
import lombok.Getter;

@Getter
public class Transaction extends AggregateRoot {
    private final Identity walletId;
    private final Identity billId;
    private final Money totalPaid;
    private final TransactionType transactionType;

    private Transaction(String id, Identity walletId, Identity billId, Money totalPaid, TransactionType transactionType) {
        super(Identity.create(id));
        this.walletId = walletId;
        this.billId = billId;
        this.totalPaid = totalPaid;
        this.transactionType = transactionType;
    }

    public static Transaction create(String id, Identity walletId, Identity billId, Money totalPaid, TransactionType transactionType) {
        Transaction transaction = new Transaction(id, walletId, billId, totalPaid, transactionType);
        addEvent(new TransactionCreatedEvent(transaction.identity));
        return transaction;
    }

    public void delete() {
        addEvent(new TransactionRemovedEvent(identity));
    }
}

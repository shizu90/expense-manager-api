package dev.gabriel.transaction.entities;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import dev.gabriel.transaction.events.TransactionCreatedEvent;
import dev.gabriel.transaction.events.TransactionRemovedEvent;
import dev.gabriel.transaction.valueobjects.TransactionId;
import lombok.Getter;

@Getter
public class Transaction extends AggregateRoot {
    private final Identity walletId;
    private final BillId billId;
    private final Money totalPaid;
    private final TransactionType transactionType;

    private Transaction(String id, Identity walletId, BillId billId, Money totalPaid, TransactionType transactionType) {
        super(TransactionId.create(id));
        this.walletId = walletId;
        this.billId = billId;
        this.totalPaid = totalPaid;
        this.transactionType = transactionType;
    }

    public static Transaction create(String id, Identity walletId, BillId billId, Money totalPaid, TransactionType transactionType) {
        Transaction transaction = new Transaction(id, walletId, billId, totalPaid, transactionType);
        transaction.addEvent(new TransactionCreatedEvent(transaction));
        return transaction;
    }

    public void remove() {
        addEvent(new TransactionRemovedEvent(this));
    }

    @Override
    public TransactionId getId() {
        return (TransactionId) id;
    }
}

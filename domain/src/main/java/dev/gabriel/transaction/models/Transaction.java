package dev.gabriel.transaction.models;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.transaction.events.TransactionCreatedEvent;
import dev.gabriel.transaction.events.TransactionDeletedEvent;
import dev.gabriel.transaction.valueobjects.TransactionId;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

@Getter
public class Transaction extends AggregateRoot {
    private WalletId walletId;
    private BillId billId;

    private Transaction(String id, WalletId walletId, BillId billId) {
        super(TransactionId.create(id));
        this.walletId = walletId;
        this.billId = billId;
    }

    public static Transaction create(String id, WalletId walletId, BillId billId) {
        Transaction transaction = new Transaction(id, walletId, billId);
        transaction.raiseEvent(new TransactionCreatedEvent(transaction.getId()));
        return transaction;
    }

    public void delete() {
        raiseEvent(new TransactionDeletedEvent(getId()));
    }

    @Override
    public TransactionId getId() {
        return (TransactionId) id;
    }
}

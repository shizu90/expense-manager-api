package dev.gabriel.bill.models;

import dev.gabriel.bill.events.BillCommentEditedEvent;
import dev.gabriel.bill.events.BillCreatedEvent;
import dev.gabriel.bill.events.BillPaidEvent;
import dev.gabriel.bill.events.BillRenamedEvent;
import dev.gabriel.bill.exceptions.BillAlreadyPaidException;
import dev.gabriel.bill.valueobjects.BillComment;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.bill.valueobjects.BillName;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.UpdatedAt;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Bill extends AggregateRoot {
    private BillName name;
    private BillComment comment;
    private Currency amount;
    private BillStatus status;
    private BillType type;
    private UserId userId;

    private Bill(String id, String name, String comment, Currency amount, BillStatus status, BillType type, UserId userId) {
        super(BillId.create(id));
        this.name = BillName.create(name);
        this.comment = BillComment.create(comment);
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.userId = userId;
    }

    public static Bill create(String id, String name, String comment, Currency amount, BillStatus status, BillType type, UserId userId) {
        Bill bill = new Bill(id, name, comment, amount, status, type, userId);
        bill.raiseEvent(new BillCreatedEvent(bill.getId()));
        return bill;
    }

    public void rename(String name) {
        this.name = BillName.create(name);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillRenamedEvent(getId()));
    }

    public void editComment(String comment) {
        this.comment = BillComment.create(comment);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillCommentEditedEvent(getId()));
    }

    public void pay() {
        if(this.status.equals(BillStatus.PAID)) {
            throw new BillAlreadyPaidException();
        }
        this.status = BillStatus.PAID;
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillPaidEvent(getId()));
    }

    @Override
    public BillId getId() {
        return (BillId) id;
    }
}

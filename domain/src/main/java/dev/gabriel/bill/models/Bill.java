package dev.gabriel.bill.models;

import dev.gabriel.bill.events.*;
import dev.gabriel.bill.exceptions.BillAlreadyPaidException;
import dev.gabriel.bill.exceptions.BillNotRecurrenceTypeException;
import dev.gabriel.bill.exceptions.BillNotYetStartedException;
import dev.gabriel.bill.valueobjects.BillComment;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.bill.valueobjects.BillName;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.UpdatedAt;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
public class Bill extends AggregateRoot {
    private BillName name;
    private BillComment comment;
    private Currency amount;
    private BillStatus status;
    private BillType type;
    private UserId userId;
    private BillRecurrence recurrence;

    private Bill(String id,
                 String name,
                 String comment,
                 Currency amount,
                 BillStatus status,
                 BillType type,
                 UserId userId
    ) {
        super(BillId.create(id));
        this.name = BillName.create(name);
        this.comment = BillComment.create(comment);
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.userId = userId;
        this.recurrence = null;
    }

    private Bill(String id,
                 String name,
                 String comment,
                 Currency amount,
                 BillStatus status,
                 BillType type,
                 UserId userId,
                 Long days,
                 LocalDate startDate
    ) {
        super(BillId.create(id));
        this.name = BillName.create(name);
        this.comment = BillComment.create(comment);
        this.amount = amount;
        this.status = status;
        this.type = type;
        this.userId = userId;
        this.recurrence = BillRecurrence.create(id, days, startDate);
    }

    public static Bill create(String id, String name, String comment, Currency amount, BillStatus status, BillType type, UserId userId) {
        Bill bill = new Bill(id, name, comment, amount, status, type, userId);
        bill.raiseEvent(new BillCreatedEvent(bill.getId()));
        return bill;
    }

    public static Bill create(String id, String name, String comment, Currency amount, BillStatus status, BillType type, UserId userId, Long days, LocalDate startDate) {
        Bill bill = new Bill(id, name, comment, amount, status, type, userId, days, startDate);
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

    public void checkRecurrence() {
        if(recurrence == null) throw new BillNotRecurrenceTypeException();

        LocalDate today = LocalDate.now();
        long periods = recurrence.countPeriods(today);

        if(recurrence.getPaidPeriods() == 0L) {
            this.status = BillStatus.UNPAID;
            updatedAt = UpdatedAt.create(Instant.now());
            return;
        }

        if(periods > recurrence.getPaidPeriods()) {
            this.status = BillStatus.UNPAID;
        }else this.status = BillStatus.PAID;
        updatedAt = UpdatedAt.create(Instant.now());
    }

    public LocalDate getNextPaymentDate() {
        if(recurrence == null) throw new BillNotRecurrenceTypeException();
        return recurrence.nextPaymentDate();
    }

    public Currency pay() {
        if(this.status.equals(BillStatus.PAID)) {
            throw new BillAlreadyPaidException();
        }

        if(recurrence != null) {
            LocalDate today = LocalDate.now();
            if(recurrence.getStartDate().isAfter(today)) {
                throw new BillNotYetStartedException();
            }
            long periodsToPay = recurrence.getPeriodsToPay();
            recurrence.incrementPeriod(periodsToPay);
            this.status = BillStatus.PAID;

            updatedAt = UpdatedAt.create(Instant.now());
            raiseEvent(new BillPaidEvent(getId()));
            return amount.multiply(periodsToPay);
        }

        this.status = BillStatus.PAID;
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillPaidEvent(getId()));
        return amount;
    }

    public void delete() {
        raiseEvent(new BillDeletedEvent(getId()));
    }

    @Override
    public BillId getId() {
        return (BillId) id;
    }
}

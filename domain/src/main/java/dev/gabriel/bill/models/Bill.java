package dev.gabriel.bill.models;

import dev.gabriel.bill.events.*;
import dev.gabriel.bill.exceptions.BillAlreadyDeletedException;
import dev.gabriel.bill.exceptions.BillAlreadyPaidException;
import dev.gabriel.bill.valueobjects.BillComment;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.bill.valueobjects.BillName;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.shared.valueobjects.UpdatedAt;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class Bill extends AggregateRoot {
    private BillName name;
    private BillComment comment;
    private Currency amount;
    private BillStatus status;
    private BillType type;
    private UserId userId;
    private CategoryId categoryId;
    private RecurringBillId recurrenceId;

    private Bill(String id,
                 String name,
                 String comment,
                 BigDecimal amount,
                 CurrencyCode currencyCode,
                 BillStatus status,
                 BillType type,
                 UserId userId,
                 CategoryId categoryId,
                 RecurringBillId recurrenceId
    ) {
        super(BillId.create(id));
        this.name = BillName.create(name);
        this.comment = BillComment.create(comment);
        this.amount = Currency.create(amount, currencyCode);
        this.status = status;
        this.type = type;
        this.userId = userId;
        this.categoryId = categoryId;
        this.recurrenceId = recurrenceId;
    }

    public static Bill create(String id,
                              String name,
                              String comment,
                              BigDecimal amount,
                              CurrencyCode currencyCode,
                              BillStatus status,
                              BillType type,
                              UserId userId,
                              CategoryId categoryId,
                              RecurringBillId recurrenceId
    ) {
        Bill bill = new Bill(id, name, comment, amount, currencyCode, status, type, userId, categoryId, recurrenceId);
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

    public void changeAmount(BigDecimal amount) {
        this.amount = Currency.create(amount, this.amount.getCurrencyCode());
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillAmountChangedEvent(getId()));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        this.amount = Currency.create(amount.getValue(), currencyCode);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillCurrencyCodeChangedEvent(getId()));
    }

    public void changeCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillCategoryChangedEvent(getId()));
    }

    public Currency pay() {
        if(this.status.equals(BillStatus.PAID)) {
            throw new BillAlreadyPaidException();
        }

        this.status = BillStatus.PAID;
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new BillPaidEvent(getId()));
        return amount;
    }

    public void delete() {
        if (isDeleted) {
            throw new BillAlreadyDeletedException(getId().getValue());
        } else {
            isDeleted = true;
            raiseEvent(new BillDeletedEvent(getId()));
        }
    }

    @Override
    public BillId getId() {
        return (BillId) id;
    }
}

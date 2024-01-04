package dev.gabriel.bill.models;

import dev.gabriel.bill.events.*;
import dev.gabriel.bill.exceptions.BillAlreadyDeletedException;
import dev.gabriel.bill.valueobjects.BillComment;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.bill.valueobjects.BillName;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class Bill extends AggregateRoot {
    private BillName name;
    private BillComment comment;
    private Currency amount;
    private BillType type;
    private WalletId walletId;
    private CategoryId categoryId;

    private Bill(String id,
                 String name,
                 String comment,
                 BigDecimal amount,
                 CurrencyCode currencyCode,
                 BillType type,
                 WalletId walletId,
                 CategoryId categoryId
    ) {
        super(BillId.create(id));
        this.name = BillName.create(name);
        this.comment = BillComment.create(comment);
        this.amount = Currency.create(amount, currencyCode);
        this.type = type;
        this.walletId = walletId;
        this.categoryId = categoryId;
    }

    public static Bill create(String id,
                              String name,
                              String comment,
                              BigDecimal amount,
                              CurrencyCode currencyCode,
                              BillType type,
                              WalletId walletId,
                              CategoryId categoryId
    ) {
        Bill bill = new Bill(id, name, comment, amount, currencyCode, type, walletId, categoryId);
        bill.raiseEvent(new BillCreatedEvent(bill.getId()));
        return bill;
    }

    public void rename(String name) {
        this.name = BillName.create(name);
        updatedAt = Instant.now();
        raiseEvent(new BillRenamedEvent(getId()));
    }

    public void editComment(String comment) {
        this.comment = BillComment.create(comment);
        updatedAt = Instant.now();
        raiseEvent(new BillCommentEditedEvent(getId()));
    }

    public void changeAmount(BigDecimal amount) {
        this.amount = Currency.create(amount, this.amount.getCurrencyCode());
        updatedAt = Instant.now();
        raiseEvent(new BillAmountChangedEvent(getId()));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        this.amount = Currency.create(amount.getValue(), currencyCode);
        updatedAt = Instant.now();
        raiseEvent(new BillCurrencyCodeChangedEvent(getId()));
    }

    public void changeCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
        updatedAt = Instant.now();
        raiseEvent(new BillCategoryChangedEvent(getId()));
    }

    public void changeType(BillType type) {
        this.type = type;
        updatedAt = Instant.now();
        raiseEvent(new BillTypeChangedEvent(getId()));
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

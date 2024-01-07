package dev.gabriel.bill.models;

import dev.gabriel.bill.events.*;
import dev.gabriel.bill.exceptions.BillAlreadyDeletedException;
import dev.gabriel.bill.valueobjects.BillComment;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.bill.valueobjects.BillName;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Bill extends AggregateRoot {
    private BillName name;
    private BillComment comment;
    private Currency amount;
    private BillType type;
    private WalletId walletId;
    private CategoryId categoryId;
    private Boolean isDeleted;

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
        BillName.validate(name);
        BillComment.validate(comment);
        raiseEvent(new BillCreatedEvent(
                id,
                getNextVersion(),
                name,
                comment,
                amount,
                currencyCode,
                type,
                walletId.getValue(),
                categoryId.getValue()
        ));
    }

    private Bill(String id, List<DomainEvent> eventStream) {
        super(BillId.create(id), eventStream);
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
        return new Bill(id, name, comment, amount, currencyCode, type, walletId, categoryId);
    }

    public static Bill create(String id, List<DomainEvent> eventStream) {
        return new Bill(id, eventStream);
    }

    public void rename(String name) {
        BillName.validate(name);
        raiseEvent(new BillRenamedEvent(getId().getValue(), getNextVersion(), name));
    }

    public void editComment(String comment) {
        BillComment.validate(comment);
        raiseEvent(new BillCommentEditedEvent(getId().getValue(), getNextVersion(), comment));
    }

    public void changeAmount(BigDecimal amount) {
        raiseEvent(new BillAmountChangedEvent(getId().getValue(), getNextVersion(), amount));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        raiseEvent(new BillCurrencyCodeChangedEvent(getId().getValue(), getNextVersion(), currencyCode));
    }

    public void changeCategory(CategoryId categoryId) {
        raiseEvent(new BillCategoryChangedEvent(getId().getValue(), getNextVersion(), categoryId.getValue()));
    }

    public void changeType(BillType type) {
        raiseEvent(new BillTypeChangedEvent(getId().getValue(), getNextVersion(), type));
    }

    public void delete() {
        if (isDeleted) {
            throw new BillAlreadyDeletedException(getId().getValue());
        } else raiseEvent(new BillDeletedEvent(getId().getValue(), getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(BillCreatedEvent event) {
        this.name = BillName.create(event.getName());
        this.comment = BillComment.create(event.getComment());
        this.amount = Currency.create(event.getAmount(), event.getCurrencyCode());
        this.type = event.getType();
        this.categoryId = CategoryId.create(event.getCategoryId());
        this.walletId = WalletId.create(event.getWalletId());
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(BillRenamedEvent event) {
        this.name = BillName.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(BillCommentEditedEvent event) {
        this.comment = BillComment.create(event.getComment());
    }

    @SuppressWarnings("unused")
    private void apply(BillAmountChangedEvent event) {
        this.amount = Currency.create(event.getAmount(), amount.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(BillCurrencyCodeChangedEvent event) {
        this.amount = Currency.create(amount.getValue(), event.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(BillTypeChangedEvent event) {
        this.type = event.getType();
    }

    @SuppressWarnings("unused")
    private void apply(BillCategoryChangedEvent event) {
        this.categoryId = CategoryId.create(event.getCategoryId());
    }

    @SuppressWarnings("unused")
    private void apply(BillDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public BillId getId() {
        return (BillId) id;
    }
}

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
        raiseEvent(new BillCreatedEvent(
                BillId.create(id),
                getNextVersion(),
                BillName.create(name),
                BillComment.create(comment),
                Currency.create(amount, currencyCode),
                type,
                walletId,
                categoryId
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
        raiseEvent(new BillRenamedEvent(getId(), getNextVersion(), BillName.create(name)));
    }

    public void editComment(String comment) {
        raiseEvent(new BillCommentEditedEvent(getId(), getNextVersion(), BillComment.create(comment)));
    }

    public void changeAmount(Currency amount) {
        raiseEvent(new BillAmountChangedEvent(getId(), getNextVersion(), amount));
    }

    public void changeCategory(CategoryId categoryId) {
        raiseEvent(new BillCategoryChangedEvent(getId(), getNextVersion(), categoryId));
    }

    public void changeType(BillType type) {
        raiseEvent(new BillTypeChangedEvent(getId(), getNextVersion(), type));
    }

    public void delete() {
        if (isDeleted) {
            throw new BillAlreadyDeletedException(getId().getValue());
        } else raiseEvent(new BillDeletedEvent(getId(), getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(BillCreatedEvent event) {
        this.name = event.getName();
        this.comment = event.getComment();
        this.amount = event.getAmount();
        this.type = event.getType();
        this.categoryId = event.getCategoryId();
        this.walletId = event.getWalletId();
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(BillRenamedEvent event) {
        this.name = event.getName();
    }

    @SuppressWarnings("unused")
    private void apply(BillCommentEditedEvent event) {
        this.comment = event.getComment();
    }

    @SuppressWarnings("unused")
    private void apply(BillAmountChangedEvent event) {
        this.amount = event.getAmount();
    }

    @SuppressWarnings("unused")
    private void apply(BillTypeChangedEvent event) {
        this.type = event.getType();
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

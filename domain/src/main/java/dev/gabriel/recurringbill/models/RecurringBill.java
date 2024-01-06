package dev.gabriel.recurringbill.models;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.events.*;
import dev.gabriel.recurringbill.exceptions.RecurringBillAlreadyDeletedException;
import dev.gabriel.recurringbill.valueobjects.*;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class RecurringBill extends AggregateRoot {
    private RecurringBillName name;
    private RecurringBillComment comment;
    private Currency amount;
    private CategoryId categoryId;
    private RecurringBillType type;
    private WalletId walletId;
    private RecurringBillRecurrence recurrence;
    private Period totalPeriods;
    private Period currentPeriods;
    private LocalDate startDate;
    private ReminderId reminderId;
    private Boolean isDeleted;

    private RecurringBill(String id,
                          String name,
                          String comment,
                          BigDecimal amount,
                          CurrencyCode currencyCode,
                          CategoryId categoryId,
                          RecurringBillType type,
                          WalletId walletId,
                          ReminderId reminderId,
                          Long recurrence,
                          Long totalPeriods,
                          LocalDate startDate
    ) {
        super(RecurringBillId.create(id));
        raiseEvent(new RecurringBillCreatedEvent(
                RecurringBillId.create(id),
                getNextVersion(),
                RecurringBillName.create(name),
                RecurringBillComment.create(comment),
                Currency.create(amount, currencyCode),
                type,
                categoryId,
                walletId,
                Period.create(totalPeriods),
                RecurringBillRecurrence.create(recurrence),
                startDate,
                reminderId)
        );
    }

    public static RecurringBill create(String id,
                                       String name,
                                       String comment,
                                       BigDecimal amount,
                                       CurrencyCode currencyCode,
                                       CategoryId categoryId,
                                       RecurringBillType type,
                                       WalletId walletId,
                                       ReminderId reminderId,
                                       Long recurrence,
                                       Long totalPeriods,
                                       LocalDate startDate
    ) {
        return new RecurringBill(id, name, comment, amount, currencyCode, categoryId, type, walletId, reminderId, recurrence, totalPeriods, startDate);
    }

    public void rename(String name) {
        raiseEvent(new RecurringBillRenamedEvent(getId(), getNextVersion(), RecurringBillName.create(name)));
    }

    public void editComment(String comment) {
        raiseEvent(new RecurringBillCommentEditedEvent(getId(), getNextVersion(), RecurringBillComment.create(comment)));
    }

    public void changeAmount(Currency amount) {
        raiseEvent(new RecurringBillAmountChangedEvent(getId(), getNextVersion(), amount));
    }

    public void changeCategory(CategoryId categoryId) {
        raiseEvent(new RecurringBillCategoryChangedEvent(getId(), getNextVersion(), categoryId));
    }

    public void changeType(RecurringBillType type) {
        raiseEvent(new RecurringBillTypeChangedEvent(getId(), getNextVersion(), type));
    }

    public void changeRecurrence(Long recurrence) {
        raiseEvent(new RecurringBillRecurrenceChangedEvent(getId(), getNextVersion(), RecurringBillRecurrence.create(recurrence)));
    }

    public void changeTotalPeriods(Long totalPeriods) {
        raiseEvent(new RecurringBillTotalPeriodsChangedEvent(getId(), getNextVersion(), Period.create(totalPeriods)));
    }

    public void changeStartDate(LocalDate startDate) {
        raiseEvent(new RecurringBillStartDateChangedEvent(getId(), getNextVersion(), startDate));
    }

    public LocalDate getNextPaymentDate() {
        return startDate.plusDays((currentPeriods.getValue() * recurrence.getValue()));
    }

    public void execute(Long numberOfPeriods) {
        long diffPeriods = totalPeriods.getValue() - currentPeriods.getValue();
        if(numberOfPeriods > diffPeriods) {
            raiseEvent(new RecurringBillExecutedEvent(getId(), getNextVersion(), currentPeriods.increment(diffPeriods)));
        }else raiseEvent(new RecurringBillExecutedEvent(getId(), getNextVersion(), currentPeriods.increment(numberOfPeriods)));
    }

    public void restart() {
        raiseEvent(new RecurringBillRestartedEvent(getId(), getNextVersion()));
    }

    public void delete() {
        if(isDeleted) {
            throw new RecurringBillAlreadyDeletedException(getId().getValue());
        }else {
            raiseEvent(new RecurringBillDeletedEvent(getId(), getNextVersion()));
        }
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCreatedEvent event) {
        this.name = event.getName();
        this.comment = event.getComment();
        this.amount = event.getAmount();
        this.type = event.getType();
        this.categoryId = event.getCategoryId();
        this.walletId = event.getWalletId();
        this.reminderId = event.getReminderId();
        this.totalPeriods = event.getTotalPeriods();
        this.recurrence = event.getRecurrence();
        this.startDate = event.getStartDate();
        this.currentPeriods = Period.create(1L);
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillRenamedEvent event) {
        this.name = event.getName();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCommentEditedEvent event) {
        this.comment = event.getComment();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillAmountChangedEvent event) {
        this.amount = event.getAmount();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillTypeChangedEvent event) {
        this.type = event.getType();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCategoryChangedEvent event) {
        this.categoryId = event.getCategoryId();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillTotalPeriodsChangedEvent event) {
        this.totalPeriods = event.getTotalPeriods();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillRecurrenceChangedEvent event) {
        this.recurrence = event.getRecurrence();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillStartDateChangedEvent event) {
        this.startDate = event.getStartDate();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillExecutedEvent event) {
        this.currentPeriods = event.getCurrentPeriods();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillRestartedEvent event) {
        this.currentPeriods = Period.create(1L);
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public RecurringBillId getId() {
        return (RecurringBillId) id;
    }
}
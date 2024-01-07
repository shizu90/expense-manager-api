package dev.gabriel.recurringbill.models;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.events.*;
import dev.gabriel.recurringbill.exceptions.RecurringBillAlreadyDeletedException;
import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.recurringbill.valueobjects.*;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
public class RecurringBill extends AggregateRoot {
    private RecurringBillName name;
    private RecurringBillComment comment;
    private Currency amount;
    private CategoryId categoryId;
    private RecurringBillType type;
    private WalletId walletId;
    private RecurringBillRecurrence recurrence;
    private RecurringBillPeriod maxPeriods;
    private RecurringBillPeriod currentPeriods;
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
                          Long maxPeriods,
                          LocalDate startDate
    ) {
        super(RecurringBillId.create(id));
        RecurringBillName.validate(name);
        RecurringBillComment.validate(comment);
        RecurringBillRecurrence.validate(recurrence, maxPeriods);
        RecurringBillPeriod.validate(maxPeriods, recurrence);
        raiseEvent(new RecurringBillCreatedEvent(
                id,
                getNextVersion(),
                name,
                comment,
                amount,
                currencyCode,
                type,
                categoryId.getValue(),
                walletId.getValue(),
                maxPeriods,
                recurrence,
                startDate,
                reminderId.getValue())
        );
    }

    private RecurringBill(String id, List<DomainEvent> events) {
        super(RecurringBillId.create(id), events);
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
                                       Long maxPeriods,
                                       LocalDate startDate
    ) {
        return new RecurringBill(id, name, comment, amount, currencyCode, categoryId, type, walletId, reminderId, recurrence, maxPeriods, startDate);
    }

    public static RecurringBill create(String id, List<DomainEvent> events) {
        return new RecurringBill(id, events);
    }

    public void rename(String name) {
        RecurringBillName.validate(name);
        raiseEvent(new RecurringBillRenamedEvent(getId().getValue(), getNextVersion(), name));
    }

    public void editComment(String comment) {
        RecurringBillComment.validate(comment);
        raiseEvent(new RecurringBillCommentEditedEvent(getId().getValue(), getNextVersion(), comment));
    }

    public void changeAmount(BigDecimal amount) {
        raiseEvent(new RecurringBillAmountChangedEvent(getId().getValue(), getNextVersion(), amount));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        raiseEvent(new RecurringBillCurrencyCodeChangedEvent(getId().getValue(), getNextVersion(), currencyCode));
    }

    public void changeCategory(CategoryId categoryId) {
        raiseEvent(new RecurringBillCategoryChangedEvent(getId().getValue(), getNextVersion(), categoryId.getValue()));
    }

    public void changeType(RecurringBillType type) {
        raiseEvent(new RecurringBillTypeChangedEvent(getId().getValue(), getNextVersion(), type));
    }

    public void changeRecurrence(Long recurrence) {
        RecurringBillRecurrence.validate(recurrence, maxPeriods.getValue());
        raiseEvent(new RecurringBillRecurrenceChangedEvent(getId().getValue(), getNextVersion(), recurrence));
    }

    public void changeMaxPeriods(Long maxPeriods) {
        RecurringBillPeriod.validate(maxPeriods, recurrence.getValue());
        raiseEvent(new RecurringBillMaxPeriodsChangedEvent(getId().getValue(), getNextVersion(), maxPeriods));
    }

    public void changeStartDate(LocalDate startDate) {
        raiseEvent(new RecurringBillStartDateChangedEvent(getId().getValue(), getNextVersion(), startDate));
    }

    public LocalDate getNextPaymentDate() {
        return startDate.plusDays((currentPeriods.getValue() * recurrence.getValue()));
    }

    public void execute(Long numberOfPeriods) {
        long diffPeriods = maxPeriods.getValue() - currentPeriods.getValue();
        if(numberOfPeriods > diffPeriods) {
            raiseEvent(new RecurringBillExecutedEvent(getId().getValue(), getNextVersion(), currentPeriods.increment(diffPeriods).getValue()));
        }else raiseEvent(new RecurringBillExecutedEvent(getId().getValue(), getNextVersion(), currentPeriods.increment(numberOfPeriods).getValue()));
    }

    public void restart() {
        raiseEvent(new RecurringBillRestartedEvent(getId().getValue(), getNextVersion()));
    }

    public void delete() {
        if(isDeleted) {
            throw new RecurringBillAlreadyDeletedException(getId().getValue());
        }else {
            raiseEvent(new RecurringBillDeletedEvent(getId().getValue(), getNextVersion()));
        }
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCreatedEvent event) {
        this.name = RecurringBillName.create(event.getName());
        this.comment = RecurringBillComment.create(event.getComment());
        this.amount = Currency.create(event.getAmount(), event.getCurrencyCode());
        this.type = event.getType();
        this.categoryId = CategoryId.create(event.getCategoryId());
        this.walletId = WalletId.create(event.getWalletId());
        this.reminderId = ReminderId.create(event.getReminderId());
        this.maxPeriods = RecurringBillPeriod.create(event.getMaxPeriods());
        this.recurrence = RecurringBillRecurrence.create(event.getRecurrence());
        this.startDate = event.getStartDate();
        this.currentPeriods = RecurringBillPeriod.create(1L);
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillRenamedEvent event) {
        this.name = RecurringBillName.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCommentEditedEvent event) {
        this.comment = RecurringBillComment.create(event.getComment());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillAmountChangedEvent event) {
        this.amount = Currency.create(event.getAmount(), amount.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCurrencyCodeChangedEvent event) {
        this.amount = Currency.create(amount.getValue(), event.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillTypeChangedEvent event) {
        this.type = event.getType();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillCategoryChangedEvent event) {
        this.categoryId = CategoryId.create(event.getCategoryId());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillMaxPeriodsChangedEvent event) {
        this.maxPeriods = RecurringBillPeriod.create(event.getMaxPeriods());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillRecurrenceChangedEvent event) {
        this.recurrence = RecurringBillRecurrence.create(event.getRecurrence());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillStartDateChangedEvent event) {
        this.startDate = event.getStartDate();
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillExecutedEvent event) {
        this.currentPeriods = RecurringBillPeriod.create(event.getCurrentPeriods());
    }

    @SuppressWarnings("unused")
    private void apply(RecurringBillRestartedEvent event) {
        this.currentPeriods = RecurringBillPeriod.create(1L);
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
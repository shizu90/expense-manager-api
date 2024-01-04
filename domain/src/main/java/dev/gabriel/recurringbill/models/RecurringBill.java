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
import java.time.Instant;
import java.time.LocalDate;

@Getter
public class RecurringBill extends AggregateRoot {
    private RecurringBillName name;
    private RecurringBillComment comment;
    private Currency amount;
    private CategoryId categoryId;
    private RecurringBillType type;
    private WalletId walletId;
    private DaysRecurrence daysRecurrence;
    private Period totalPeriods;
    private Period currentPeriods;
    private LocalDate startDate;
    private ReminderId reminderId;

    private RecurringBill(String id,
                          String name,
                          String comment,
                          BigDecimal amount,
                          CurrencyCode currencyCode,
                          CategoryId categoryId,
                          RecurringBillType type,
                          WalletId walletId,
                          Long daysRecurrence,
                          Long totalPeriods,
                          LocalDate startDate
    ) {
        super(RecurringBillId.create(id));
        this.name = RecurringBillName.create(name);
        this.comment = RecurringBillComment.create(comment);
        this.amount = Currency.create(amount, currencyCode);
        this.categoryId = categoryId;
        this.type = type;
        this.walletId = walletId;
        this.daysRecurrence = DaysRecurrence.create(daysRecurrence);
        this.startDate = startDate;
        this.totalPeriods = Period.create(totalPeriods);
        this.currentPeriods = Period.create(1L);
        this.reminderId = null;
    }

    public static RecurringBill create(String id,
                                       String name,
                                       String comment,
                                       BigDecimal amount,
                                       CurrencyCode currencyCode,
                                       CategoryId categoryId,
                                       RecurringBillType type,
                                       WalletId walletId,
                                       Long daysRecurrence,
                                       Long totalPeriods,
                                       LocalDate startDate
    ) {
        RecurringBill recurringBill = new RecurringBill(
                id, name, comment, amount, currencyCode, categoryId, type, walletId, daysRecurrence, totalPeriods, startDate
        );
        recurringBill.raiseEvent(new RecurringBillCreatedEvent(recurringBill.getId()));
        return recurringBill;
    }

    public void rename(String name) {
        this.name = RecurringBillName.create(name);
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillRenamedEvent(getId()));
    }

    public void editComment(String comment) {
        this.comment = RecurringBillComment.create(comment);
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillCommentEditedEvent(getId()));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        amount = Currency.create(amount.getValue(), currencyCode);
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillCurrencyCodeChangedEvent(getId()));
    }

    public void changeCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillCategoryChangedEvent(getId()));
    }

    public void changeAmount(BigDecimal amount) {
        this.amount = Currency.create(amount, this.amount.getCurrencyCode());
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillAmountChangedEvent(getId()));
    }

    public void changeType(RecurringBillType type) {
        this.type = type;
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillTypeChangedEvent(getId()));
    }

    public void changeDaysRecurrence(Long daysRecurrence) {
        this.daysRecurrence = DaysRecurrence.create(daysRecurrence);
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillDaysRecurrenceChangedEvent(getId()));
    }

    public void changeTotalPeriods(Long totalPeriods) {
        this.totalPeriods = Period.create(totalPeriods);
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillTotalPeriodsChangedEvent(getId()));
    }

    public void changeStartDate(LocalDate startDate) {
        this.startDate = startDate;
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillStartDateChangedEvent(getId()));
    }

    public LocalDate getNextPaymentDate() {
        return startDate.plusDays((currentPeriods.getValue() * daysRecurrence.getValue()));
    }

    public void execute(Long numberOfPeriods) {
        long diffPeriods = totalPeriods.getValue() - currentPeriods.getValue();
        if(numberOfPeriods > diffPeriods) {
            currentPeriods = currentPeriods.increment(diffPeriods);
        }else currentPeriods = currentPeriods.increment(numberOfPeriods);

        updatedAt = Instant.now();
        raiseEvent(new RecurringBillExecutedEvent(getId()));
    }

    public void restart() {
        currentPeriods = currentPeriods.decrement(currentPeriods.getValue() - 1);
        updatedAt = Instant.now();
        raiseEvent(new RecurringBillRestartedEvent(getId()));
    }

    public void setupReminder(ReminderId reminderId) {
        this.reminderId = reminderId;
        updatedAt = Instant.now();
    }

    public void delete() {
        if(isDeleted) {
            throw new RecurringBillAlreadyDeletedException(getId().getValue());
        }else {
            isDeleted = true;
            raiseEvent(new RecurringBillDeletedEvent(getId()));
        }
    }

    @Override
    public RecurringBillId getId() {
        return (RecurringBillId) id;
    }
}
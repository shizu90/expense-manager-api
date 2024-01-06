package dev.gabriel.recurringbill.events;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.recurringbill.valueobjects.*;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RecurringBillCreatedEvent extends RecurringBillEvent {
    private final RecurringBillName name;
    private final RecurringBillComment comment;
    private final Currency amount;
    private final RecurringBillType type;
    private final CategoryId categoryId;
    private final WalletId walletId;
    private final Period totalPeriods;
    private final RecurringBillRecurrence recurrence;
    private final LocalDate startDate;
    private final ReminderId reminderId;

    public RecurringBillCreatedEvent(RecurringBillId recurringBillId,
                                     Long version,
                                     RecurringBillName name,
                                     RecurringBillComment comment,
                                     Currency amount,
                                     RecurringBillType type,
                                     CategoryId categoryId,
                                     WalletId walletId,
                                     Period totalPeriods,
                                     RecurringBillRecurrence recurrence,
                                     LocalDate startDate,
                                     ReminderId reminderId
    ) {
        super(recurringBillId, version);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.walletId = walletId;
        this.totalPeriods = totalPeriods;
        this.recurrence = recurrence;
        this.startDate = startDate;
        this.reminderId = reminderId;
    }
}

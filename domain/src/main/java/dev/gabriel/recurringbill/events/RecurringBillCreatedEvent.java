package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class RecurringBillCreatedEvent extends RecurringBillEvent {
    private final String name;
    private final String comment;
    private final BigDecimal amount;
    private final CurrencyCode currencyCode;
    private final RecurringBillType type;
    private final String categoryId;
    private final String walletId;
    private final Long maxPeriods;
    private final Long recurrence;
    private final LocalDate startDate;
    private final String reminderId;

    public RecurringBillCreatedEvent(String recurringBillId,
                                     Long version,
                                     String name,
                                     String comment,
                                     BigDecimal amount,
                                     CurrencyCode currencyCode,
                                     RecurringBillType type,
                                     String categoryId,
                                     String walletId,
                                     Long maxPeriods,
                                     Long recurrence,
                                     LocalDate startDate,
                                     String reminderId
    ) {
        super(recurringBillId, version);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.type = type;
        this.categoryId = categoryId;
        this.walletId = walletId;
        this.maxPeriods = maxPeriods;
        this.recurrence = recurrence;
        this.startDate = startDate;
        this.reminderId = reminderId;
    }
}

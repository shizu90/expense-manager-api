package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class RecurringBillCreatedEvent extends RecurringBillEvent {
    private final String name;
    private final String comment;
    private final BigDecimal amount;
    private final CurrencyCode currencyCode;
    private final RecurringBillType type;
    private final UUID categoryId;
    private final UUID walletId;
    private final Long maxPeriods;
    private final Long recurrence;
    private final LocalDate startDate;
    private final UUID reminderId;

    public RecurringBillCreatedEvent(UUID recurringBillId,
                                     Long version,
                                     String name,
                                     String comment,
                                     BigDecimal amount,
                                     CurrencyCode currencyCode,
                                     RecurringBillType type,
                                     UUID categoryId,
                                     UUID walletId,
                                     Long maxPeriods,
                                     Long recurrence,
                                     LocalDate startDate,
                                     UUID reminderId
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

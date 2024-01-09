package dev.gabriel.bill.events;

import dev.gabriel.bill.models.BillType;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class BillCreatedEvent extends BillEvent {
    private final String name;
    private final String comment;
    private final BigDecimal amount;
    private final CurrencyCode currencyCode;
    private final BillType type;
    private final UUID walletId;
    private final UUID categoryId;

    public BillCreatedEvent(UUID billId,
                            Long version,
                            String name,
                            String comment,
                            BigDecimal amount,
                            CurrencyCode currencyCode,
                            BillType type,
                            UUID walletId,
                            UUID categoryId
    ) {
        super(billId, version);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.type = type;
        this.walletId = walletId;
        this.categoryId = categoryId;
    }
}

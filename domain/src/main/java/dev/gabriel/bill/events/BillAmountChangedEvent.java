package dev.gabriel.bill.events;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class BillAmountChangedEvent extends BillEvent {
    private final BigDecimal amount;

    public BillAmountChangedEvent(String billId, Long version, BigDecimal amount) {
        super(billId, version);
        this.amount = amount;
    }
}

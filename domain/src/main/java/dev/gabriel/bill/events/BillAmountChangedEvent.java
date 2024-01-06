package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.valueobjects.Currency;
import lombok.Getter;

@Getter
public class BillAmountChangedEvent extends BillEvent {
    private final Currency amount;

    public BillAmountChangedEvent(BillId billId, Long version, Currency amount) {
        super(billId, version);
        this.amount = amount;
    }
}

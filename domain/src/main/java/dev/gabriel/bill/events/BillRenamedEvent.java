package dev.gabriel.bill.events;

import lombok.Getter;

@Getter
public class BillRenamedEvent extends BillEvent {
    private final String name;

    public BillRenamedEvent(String billId, Long version, String name) {
        super(billId, version);
        this.name = name;
    }
}

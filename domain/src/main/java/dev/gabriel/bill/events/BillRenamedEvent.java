package dev.gabriel.bill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BillRenamedEvent extends BillEvent {
    private final String name;

    public BillRenamedEvent(UUID billId, Long version, String name) {
        super(billId, version);
        this.name = name;
    }
}

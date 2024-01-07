package dev.gabriel.bill.events;

public class BillDeletedEvent extends BillEvent {
    public BillDeletedEvent(String billId, Long version) {
        super(billId, version);
    }
}

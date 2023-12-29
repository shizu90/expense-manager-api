package dev.gabriel.bill.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class BillRecurrenceId extends Identity {
    private BillRecurrenceId(String value) {
        super(value);
    }

    public static BillRecurrenceId create(String value) {
        return new BillRecurrenceId(value);
    }
}

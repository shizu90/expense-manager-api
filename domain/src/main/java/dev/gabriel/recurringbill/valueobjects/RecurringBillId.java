package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class RecurringBillId extends Identity {
    private RecurringBillId(String value) {
        super(value);
    }

    public static RecurringBillId create(String value) {
        return new RecurringBillId(value);
    }
}

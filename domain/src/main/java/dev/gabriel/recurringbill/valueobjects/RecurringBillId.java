package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class RecurringBillId extends Identity {
    private RecurringBillId(UUID value) {
        super(value);
    }

    public static RecurringBillId create(UUID value) {
        return new RecurringBillId(value);
    }
}

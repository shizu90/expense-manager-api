package dev.gabriel.bill.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class BillId extends Identity {
    private BillId(UUID value) {
        super(value);
    }

    public static BillId create(UUID value) {
        return new BillId(value);
    }
}

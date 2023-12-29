package dev.gabriel.bill.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class BillId extends Identity {
    private BillId(String value) {
        super(value);
    }

    public static BillId create(String value) {
        return new BillId(value);
    }
}

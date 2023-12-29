package dev.gabriel.billgroup.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class BillGroupId extends Identity {
    private BillGroupId(String value) {
        super(value);
    }

    public static BillGroupId create(String value) {
        return new BillGroupId(value);
    }
}

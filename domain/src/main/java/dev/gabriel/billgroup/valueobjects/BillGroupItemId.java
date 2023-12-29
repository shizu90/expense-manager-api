package dev.gabriel.billgroup.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class BillGroupItemId extends Identity {
    private BillGroupItemId(String value) {
        super(value);
    }

    public static BillGroupItemId create(String value) {
        return new BillGroupItemId(value);
    }
}

package dev.gabriel.bill.models;

public enum BillStatus {
    PAID("paid"),
    UNPAID("unpaid");

    private final String value;

    BillStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

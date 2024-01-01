package dev.gabriel.bill.models;

public enum BillType {
    IN("in"),
    OUT("out");

    private final String value;

    BillType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

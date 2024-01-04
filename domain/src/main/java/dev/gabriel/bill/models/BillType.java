package dev.gabriel.bill.models;

import dev.gabriel.bill.exceptions.BillValidationException;

public enum BillType {
    INCOME("income"),
    EXPENSE("expense");

    private final String value;

    BillType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static BillType getConstant(String value) {
        return switch(value) {
            case "income" -> BillType.INCOME;
            case "expense" -> BillType.EXPENSE;
            default -> throw new BillValidationException("Type", "Not a valid type.");
        };
    }
}

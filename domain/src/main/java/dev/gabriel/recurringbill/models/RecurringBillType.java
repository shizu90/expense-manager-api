package dev.gabriel.recurringbill.models;

import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;

public enum RecurringBillType {
    INCOME("income"),
    EXPENSE("expense");

    private final String value;

    RecurringBillType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static RecurringBillType getConstant(String value) {
        return switch (value) {
            case "income" -> RecurringBillType.INCOME;
            case "expense" -> RecurringBillType.EXPENSE;
            default -> throw new RecurringBillValidationException("Type", "Not a valid type.");
        };
    }
}

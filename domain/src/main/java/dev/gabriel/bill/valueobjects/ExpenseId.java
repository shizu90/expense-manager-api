package dev.gabriel.bill.valueobjects;

public class ExpenseId extends BillId {
    private ExpenseId(String id) {
        super(id);
    }

    public static ExpenseId create(String id) {
        return new ExpenseId(id);
    }
}

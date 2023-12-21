package dev.gabriel.bill.valueobjects;

public class IncomeId extends BillId {
    private IncomeId(String id) {
        super(id);
    }

    public static IncomeId create(String id) {
        return new IncomeId(id);
    }
}

package dev.gabriel.bill.entities.income;

import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

public class CommonIncome extends Income {
    private CommonIncome(String id, String name, String comment, Money amount, IncomeCategory category, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
    }

    public static CommonIncome create(String id, String name, String comment, Money amount, IncomeCategory category, BillStatus status, Identity userId) {
        return new CommonIncome(id, name, comment, amount, category, status, userId);
    }
}

package dev.gabriel.entities.bill.income;

import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;

public class CommonIncome extends Income {
    private CommonIncome(String id, String name, String comment, Money amount, IncomeCategory category, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
    }

    protected static CommonIncome create(String id, String name, String comment, Money amount, IncomeCategory category, BillStatus status, Identity userId) {
        return new CommonIncome(id, name, comment, amount, category, status, userId);
    }
}

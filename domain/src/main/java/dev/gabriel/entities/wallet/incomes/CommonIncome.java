package dev.gabriel.entities.wallet.incomes;

import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.valueobjects.Money;

public class CommonIncome extends Income {
    private CommonIncome(String id, String name, String comment, Money amount, IncomeCategory category) {
        super(id, name, comment, amount, category);
    }

    protected static CommonIncome create(String id, String name, String comment, Money amount, IncomeCategory category) {
        return new CommonIncome(id, name, comment, amount, category);
    }
}

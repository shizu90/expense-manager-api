package dev.gabriel.entities;

import dev.gabriel.entities.enums.IncomeCategory;
import dev.gabriel.valueobjects.Money;

public class CommonIncome extends Income {
    private CommonIncome(Long id, String name, String comment, Money amount, IncomeCategory category) {
        super(id, name, comment, amount, category);
    }

    public static CommonIncome create(Long id, String name, String comment, Money amount, IncomeCategory category) {
        return new CommonIncome(id, name, comment, amount, category);
    }
}

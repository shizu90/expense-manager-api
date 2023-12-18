package dev.gabriel.entities.wallet.incomes;

import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.entities.wallet.Bill;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Income extends Bill {
    protected IncomeCategory category;

    protected Income(String id, String name, String comment, Money amount, IncomeCategory category) {
        super(id, name, comment, amount);
        this.category = category;
    }
}

package dev.gabriel.entities.bill.income;

import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.entities.bill.Bill;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Income extends Bill {
    protected IncomeCategory category;

    protected Income(String id, String name, String comment, Money amount, IncomeCategory category, BillStatus status, Identity userId) {
        super(id, name, comment, amount, status, userId);
        this.category = category;
    }
}

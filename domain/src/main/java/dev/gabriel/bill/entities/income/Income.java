package dev.gabriel.bill.entities.income;

import dev.gabriel.bill.entities.Bill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.valueobjects.IncomeId;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Income extends Bill {
    protected IncomeCategory category;

    protected Income(String id, String name, String comment, Money amount, IncomeCategory category, BillStatus status, Identity userId) {
        super(IncomeId.create(id), name, comment, amount, status, userId);
        this.category = category;
    }

    @Override
    public IncomeId getId() {
        return (IncomeId) id;
    }
}

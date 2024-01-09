package dev.gabriel.budget.models;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.budget.valueobjects.BudgetItemId;
import dev.gabriel.shared.models.Entity;
import lombok.Getter;

import java.util.UUID;

@Getter
public class BudgetItem extends Entity {
    private BillId billId;
    private BudgetId billGroupId;

    private BudgetItem(UUID id, BillId billId, BudgetId billGroupId) {
        super(BudgetItemId.create(id));
        this.billId = billId;
        this.billGroupId = billGroupId;
    }

    public static BudgetItem create(UUID id, BillId billId, BudgetId billGroupId) {
        return new BudgetItem(id, billId, billGroupId);
    }

    @Override
    public BudgetItemId getId() {
        return (BudgetItemId) id;
    }
}

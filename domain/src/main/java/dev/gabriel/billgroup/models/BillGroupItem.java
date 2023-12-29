package dev.gabriel.billgroup.models;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.billgroup.valueobjects.BillGroupId;
import dev.gabriel.billgroup.valueobjects.BillGroupItemId;
import dev.gabriel.shared.models.Entity;
import lombok.Getter;

@Getter
public class BillGroupItem extends Entity {
    private BillId billId;
    private BillGroupId billGroupId;

    private BillGroupItem(String id, BillId billId, BillGroupId billGroupId) {
        super(BillGroupItemId.create(id));
        this.billId = billId;
        this.billGroupId = billGroupId;
    }

    public static BillGroupItem create(String id, BillId billId, BillGroupId billGroupId) {
        return new BillGroupItem(id, billId, billGroupId);
    }

    @Override
    public BillGroupItemId getId() {
        return (BillGroupItemId) id;
    }
}

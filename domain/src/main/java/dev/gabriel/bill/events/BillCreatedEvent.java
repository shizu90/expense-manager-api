package dev.gabriel.bill.events;

import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.valueobjects.BillComment;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.bill.valueobjects.BillName;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

@Getter
public class BillCreatedEvent extends BillEvent {
    private final BillName name;
    private final BillComment comment;
    private final Currency amount;
    private final BillType type;
    private final WalletId walletId;
    private final CategoryId categoryId;

    public BillCreatedEvent(BillId billId, Long version, BillName name, BillComment comment, Currency amount, BillType type, WalletId walletId, CategoryId categoryId) {
        super(billId, version);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.type = type;
        this.walletId = walletId;
        this.categoryId = categoryId;
    }
}

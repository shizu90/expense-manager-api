package dev.gabriel.bill.entities;

import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import lombok.Getter;

@Getter
public abstract class Bill extends AggregateRoot {
    protected String name;
    protected String comment;
    protected Money amount;
    protected BillStatus status;
    protected Identity userId;

    public Bill(String id, String name, String comment, Money amount, BillStatus status, Identity userId) {
        super(id);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }

    public void rename(String name) {
        this.name = name;
    }

    public void changeComment(String comment) {
        this.comment = comment;
    }

    public void updateStatus(BillStatus status) {
        this.status = status;
    }

    public void changeAmount(Money amount) {
        updateStatus(BillStatus.UNPAID);
        this.amount = amount;
    }
}

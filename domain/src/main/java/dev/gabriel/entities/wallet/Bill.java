package dev.gabriel.entities.wallet;

import dev.gabriel.primitives.Entity;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Bill extends Entity {
    protected String name;
    protected String comment;
    protected Date createdAt;
    protected Money amount;

    public Bill(String id, String name, String comment, Money amount) {
        super(id);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.createdAt = new Date();
    }
}

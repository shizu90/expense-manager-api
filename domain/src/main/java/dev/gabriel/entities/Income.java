package dev.gabriel.entities;

import dev.gabriel.entities.enums.IncomeCategory;
import dev.gabriel.primitives.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Income extends Entity {
    protected String name;
    protected String comment;
    protected Date createdAt;
    protected Double amount;
    protected IncomeCategory category;

    protected Income(Long id, String name, String comment, Double amount, IncomeCategory category) {
        super(id);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.category = category;
        this.createdAt = new Date();
    }
}

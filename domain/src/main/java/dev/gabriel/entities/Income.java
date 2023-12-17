package dev.gabriel.entities;

import dev.gabriel.entities.enums.IncomeCategory;
import dev.gabriel.primitives.Entity;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Income extends Bill {
    protected IncomeCategory category;

    protected Income(Long id, String name, String comment, Money amount, IncomeCategory category) {
        super(id, name, comment, amount);
        this.category = category;
    }
}

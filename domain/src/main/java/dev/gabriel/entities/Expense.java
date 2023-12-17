package dev.gabriel.entities;

import dev.gabriel.entities.enums.ExpenseCategory;
import dev.gabriel.primitives.Entity;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public abstract class Expense extends Bill {
    protected ExpenseCategory category;

    protected Expense(Long id, String name, String comment, Money amount, ExpenseCategory category) {
        super(id, name, comment, amount);
        this.category = category;
    }
}
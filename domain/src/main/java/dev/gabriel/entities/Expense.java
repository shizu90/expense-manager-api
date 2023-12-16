package dev.gabriel.entities;

import dev.gabriel.entities.enums.ExpenseCategory;
import dev.gabriel.primitives.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public abstract class Expense extends Entity {
    protected String name;
    protected String comment;
    protected Date createdAt;
    protected Double amount;
    protected ExpenseCategory category;

    protected Expense(Long id, String name, String comment, Double amount, ExpenseCategory category) {
        super(id);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.category = category;
        this.createdAt = new Date();
    }
}
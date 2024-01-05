package dev.gabriel.budget.projection;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.budget.models.Budget;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "budget_items")
@Getter
@Setter
public class BudgetItemProjection {
    @Id
    private String id;
    @OneToOne
    private Bill bill;
    @ManyToOne
    private Budget budget;

    public BudgetItemProjection(String id, Bill bill, Budget budget) {
        this.id = id;
        this.bill = bill;
        this.budget = budget;
    }
}

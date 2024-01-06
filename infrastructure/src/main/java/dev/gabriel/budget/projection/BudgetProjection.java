package dev.gabriel.budget.projection;

import dev.gabriel.shared.projection.AggregateRootProjection;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.models.User;
import dev.gabriel.user.projection.UserProjection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "budgets")
@Getter
@Setter
public class BudgetProjection extends AggregateRootProjection {
    @Id
    private String id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code")
    private CurrencyCode currencyCode;
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @ManyToOne
    private UserProjection owner;
    @OneToMany(mappedBy = "budget")
    private List<BudgetItemProjection> bills;

    public BudgetProjection(String id, String name, String description, CurrencyCode currencyCode, BigDecimal totalAmount, User user) {
        super(Instant.now(), Instant.now(), false);
        this.id = id;
        this.name = name;
        this.description = description;
        this.currencyCode = currencyCode;
        this.totalAmount = totalAmount;
        this.owner = user;
    }
}

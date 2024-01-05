package dev.gabriel.bill.projection;

import dev.gabriel.bill.models.BillType;
import dev.gabriel.category.projection.CategoryProjection;
import dev.gabriel.shared.projection.AggregateRootProjection;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.projection.WalletProjection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "bills")
@Getter
@Setter
public class BillProjection extends AggregateRootProjection {
    @Id
    private String id;
    private String comment;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;
    @Enumerated(EnumType.STRING)
    private BillType type;
    @ManyToOne
    private CategoryProjection category;
    @ManyToOne
    private WalletProjection wallet;

    public BillProjection(String id, String comment, BigDecimal amount, CurrencyCode currencyCode, BillType type, CategoryProjection category, WalletProjection wallet) {
        super(Instant.now(), Instant.now(), false);
        this.id = id;
        this.comment = comment;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.type = type;
        this.category = category;
        this.wallet = wallet;
    }
}

package dev.gabriel.wallet.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.gabriel.bill.projection.BillProjection;
import dev.gabriel.shared.projection.AggregateRootProjection;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.projection.UserProjection;
import dev.gabriel.wallet.models.WalletType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "wallets")
@Getter
@Setter
public class WalletProjection extends AggregateRootProjection {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal balance;
    @Column(name = "initial_balance")
    private BigDecimal initialBalance;
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code")
    private CurrencyCode currencyCode;
    @Column(name = "is_main")
    private Boolean isMain;
    @Column(name = "last_balance_update")
    private Instant lastBalanceUpdate;
    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_type")
    private WalletType walletType;
    @ManyToOne
    @JsonIgnore
    private UserProjection owner;
    @OneToMany(mappedBy = "wallet")
    private List<BillProjection> bills;

    public WalletProjection(String id, String name, String description, BigDecimal balance, CurrencyCode currencyCode, Boolean isMain, WalletType walletType, UserProjection user) {
        super(Instant.now(), Instant.now(), false);
        this.id = id;
        this.name = name;
        this.description = description;
        this.balance = balance;
        this.initialBalance = balance;
        this.currencyCode = currencyCode;
        this.isMain = isMain;
        this.walletType = walletType;
        this.owner = user;
    }
}

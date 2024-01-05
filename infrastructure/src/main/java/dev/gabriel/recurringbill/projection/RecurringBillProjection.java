package dev.gabriel.recurringbill.projection;

import dev.gabriel.category.projection.CategoryProjection;
import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.reminder.projection.ReminderProjection;
import dev.gabriel.shared.projection.AggregateRootProjection;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.projection.WalletProjection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "recurring_bills")
@Getter
@Setter
public class RecurringBillProjection extends AggregateRootProjection {
    @Id
    private String id;
    private String name;
    private String comment;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;
    @Enumerated(EnumType.STRING)
    private RecurringBillType type;
    private Long daysRecurrence;
    private Long totalPeriods;
    private Long currentPeriods;
    private LocalDate startDate;
    @ManyToOne
    private WalletProjection wallet;
    @ManyToOne
    private CategoryProjection category;
    @OneToOne
    private ReminderProjection reminder;

    public RecurringBillProjection(String id,
                                   String name,
                                   String comment,
                                   BigDecimal amount,
                                   CurrencyCode currencyCode,
                                   RecurringBillType type,
                                   Long daysRecurrence,
                                   Long currentPeriods,
                                   LocalDate startDate,
                                   WalletProjection wallet,
                                   CategoryProjection category,
                                   ReminderProjection reminder
    ) {
        super(Instant.now(), Instant.now(), false);
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.type = type;
        this.daysRecurrence = daysRecurrence;
        this.currentPeriods = currentPeriods;
        this.startDate = startDate;
        this.wallet = wallet;
        this.category = category;
        this.reminder = reminder;
    }
}

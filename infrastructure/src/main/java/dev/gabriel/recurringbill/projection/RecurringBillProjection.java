package dev.gabriel.recurringbill.projection;

import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "RecurringBills")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class RecurringBillProjection {
    @Id
    private String id;
    private String name;
    private String comment;
    private BigDecimal amount;
    private CurrencyCode currencyCode;
    private RecurringBillType type;
    private String category;
    private Long recurrence;
    private Long maxPeriods;
    private Long currentPeriods;
    private LocalDate startDate;
    private String walletId;
    private String reminderId;

    public static RecurringBillProjection create(String id,
                                                 String name,
                                                 String comment,
                                                 BigDecimal amount,
                                                 CurrencyCode currencyCode,
                                                 RecurringBillType type,
                                                 String category,
                                                 Long recurrence,
                                                 Long maxPeriods,
                                                 Long currentPeriods,
                                                 LocalDate startDate,
                                                 String walletId,
                                                 String reminderId
    ) {
        return new RecurringBillProjection(
                id,
                name,
                comment,
                amount,
                currencyCode,
                type,
                category,
                recurrence,
                maxPeriods,
                currentPeriods,
                startDate,
                walletId,
                reminderId
        );
    }
}

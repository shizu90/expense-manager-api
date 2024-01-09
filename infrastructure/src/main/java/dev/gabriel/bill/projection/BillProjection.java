package dev.gabriel.bill.projection;

import dev.gabriel.bill.models.BillType;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "Bills")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class BillProjection {
    @Id
    private String id;
    private String name;
    private String comment;
    private BigDecimal amount;
    private CurrencyCode currencyCode;
    private BillType type;
    private String category;
    private String walletId;

    public static BillProjection create(String id,
                                        String name,
                                        String comment,
                                        BigDecimal amount,
                                        CurrencyCode currencyCode,
                                        BillType type,
                                        String category,
                                        String walletId
    ) {
        return new BillProjection(id, name, comment, amount, currencyCode, type, category, walletId);
    }
}

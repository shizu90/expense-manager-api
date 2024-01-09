package dev.gabriel.budget.projection;

import dev.gabriel.bill.projection.BillProjection;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "Budgets")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class BudgetProjection {
    @Id
    private String id;
    private String name;
    private String description;
    private CurrencyCode currencyCode;
    private BigDecimal totalAmount;
    private String ownerId;
    private List<BillProjection> bills;

    public static BudgetProjection create(String id,
                                          String name,
                                          String description,
                                          CurrencyCode currencyCode,
                                          BigDecimal totalAmount,
                                          String ownerId,
                                          List<BillProjection> bills
    ) {
        return new BudgetProjection(id, name, description, currencyCode, totalAmount, ownerId, bills);
    }
}

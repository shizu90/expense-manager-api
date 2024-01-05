package dev.gabriel.category.projection;

import dev.gabriel.bill.projection.BillProjection;
import dev.gabriel.recurringbill.projection.RecurringBillProjection;
import dev.gabriel.shared.projection.AggregateRootProjection;
import dev.gabriel.user.projection.UserProjection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class CategoryProjection extends AggregateRootProjection {
    @Id
    private String id;
    private String name;
    @ManyToOne
    private UserProjection owner;
    @OneToMany(mappedBy = "category")
    private List<BillProjection> bills;
    @OneToMany(mappedBy = "category")
    private List<RecurringBillProjection> recurringBills;

    public CategoryProjection(String id, String name, UserProjection user) {
        super(Instant.now(), Instant.now(), false);
        this.id = id;
        this.name = name;
        this.owner = user;
    }
}

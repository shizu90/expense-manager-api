package dev.gabriel.user.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.gabriel.category.projection.CategoryProjection;
import dev.gabriel.reminder.projection.ReminderProjection;
import dev.gabriel.shared.projection.AggregateRootProjection;
import dev.gabriel.wallet.projection.WalletProjection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserProjection extends AggregateRootProjection {
    @Id
    private String id;
    private String email;
    private String name;
    private String password;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<WalletProjection> wallets;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<CategoryProjection> categories;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<ReminderProjection> reminders;

    public UserProjection(String id, String email, String name, String password, Boolean isActive) {
        super(Instant.now(), Instant.now(), false);
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.isActive = isActive;
    }
}

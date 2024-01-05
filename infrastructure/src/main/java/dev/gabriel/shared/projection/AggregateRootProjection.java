package dev.gabriel.shared.projection;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
public class AggregateRootProjection {
    @Column(name = "created_at")
    protected Instant createdAt;
    @Column(name = "updated_at")
    protected Instant updatedAt;
    @Column(name = "is_deleted")
    protected Boolean isDeleted;
}

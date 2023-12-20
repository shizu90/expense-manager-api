package dev.gabriel.primitives;

import dev.gabriel.valueobjects.Identity;
import lombok.Getter;

import java.time.LocalDate;

public abstract class Entity {
    private Identity identity;
    @Getter private LocalDate createdAt;

    protected Entity(String id) {
        this.identity = Identity.create(id);
        this.createdAt = LocalDate.now();
    }

    protected Entity() {}

    public String getIdentity() {
        return (String) identity.getAtomicValues().get(0);
    }
}

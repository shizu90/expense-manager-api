package dev.gabriel.primitives;

import dev.gabriel.valueobjects.Identity;

public abstract class Entity {
    private Identity id;

    protected Entity(Long id) {
        this.id = Identity.create(id);
    }

    protected Entity() {}

    public Long getIdentity() {
        return (Long) id.getAtomicValues().get(0);
    }
}

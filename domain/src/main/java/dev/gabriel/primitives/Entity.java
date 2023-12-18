package dev.gabriel.primitives;

import dev.gabriel.valueobjects.Identity;

public abstract class Entity {
    private Identity id;

    protected Entity(String id) {
        this.id = Identity.create(id);
    }

    protected Entity() {}

    public String getIdentity() {
        return (String) id.getAtomicValues().get(0);
    }
}

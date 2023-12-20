package dev.gabriel.shared.entities;

import dev.gabriel.shared.valueobjects.Identity;

public abstract class AggregateRoot extends Entity {
    protected AggregateRoot(Identity id) {
        super(id);
    }
}

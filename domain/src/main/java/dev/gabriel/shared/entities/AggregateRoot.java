package dev.gabriel.shared.entities;

public abstract class AggregateRoot extends Entity {
    protected AggregateRoot(String id) {
        super(id);
    }
}

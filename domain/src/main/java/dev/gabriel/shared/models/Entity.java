package dev.gabriel.shared.models;

import dev.gabriel.shared.valueobjects.Identity;
import lombok.Getter;


@Getter
public abstract class Entity {
    protected final Identity id;

    protected Entity(Identity id) {
        this.id = id;
    }

    public abstract Identity getId();
}

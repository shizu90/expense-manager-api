package dev.gabriel.shared.valueobjects;

public class DomainEventId extends Identity {
    private DomainEventId(String id) {
        super(id);
    }

    public static DomainEventId create(String id) {
        return new DomainEventId(id);
    }
}

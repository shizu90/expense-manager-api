package dev.gabriel.shared.models;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.exceptions.EventHandlerNotImplementedException;
import dev.gabriel.shared.valueobjects.Identity;
import lombok.Getter;

import static java.util.Collections.emptyList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AggregateRoot extends Entity {
    protected Long baseVersion;
    protected List<DomainEvent> events;

    protected AggregateRoot(Identity id) {
        this(id, emptyList());
        this.baseVersion = 1L;
    }

    protected AggregateRoot(Identity id, List<DomainEvent> events) {
        super(id);
        events.forEach(event -> {
            apply(event);
            this.baseVersion = event.getVersion();
        });
        this.events = new ArrayList<>();
    }

    private void apply(DomainEvent event) {
        try {
            Method method = this.getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        }catch(InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new EventHandlerNotImplementedException();
        }
    }

    protected void raiseEvent(DomainEvent domainEvent) {
        apply(domainEvent);
        events.add(domainEvent);
    }

    protected Long getNextVersion() {
        return baseVersion + events.size() + 1;
    }
}

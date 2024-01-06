package dev.gabriel.shared.exceptions;

public class EventHandlerNotImplementedException extends DomainException {
    public EventHandlerNotImplementedException() {
        super("Event handler not yet implemented.");
    }
}

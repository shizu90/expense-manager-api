package dev.gabriel.shared.exceptions;

public class CommandHandlerNotImplementedException extends RuntimeException {
    public CommandHandlerNotImplementedException() {
        super("Command handler is not implemented.");
    }
}

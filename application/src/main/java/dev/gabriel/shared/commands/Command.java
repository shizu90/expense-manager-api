package dev.gabriel.shared.commands;

public abstract class Command {
    public Class<?> getType() {
        return getClass();
    }
}

package dev.gabriel.shared.bus;

import dev.gabriel.shared.commands.Command;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.models.AggregateRoot;

public interface ICommandBus {
    <R extends AggregateRoot, C extends Command> R execute(C command);
}

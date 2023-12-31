package dev.gabriel.shared.handlers;

import dev.gabriel.shared.commands.ICommand;
import dev.gabriel.shared.models.AggregateRoot;

public interface ICommandHandler<R extends AggregateRoot, C extends ICommand> {
    R execute(C command);
}

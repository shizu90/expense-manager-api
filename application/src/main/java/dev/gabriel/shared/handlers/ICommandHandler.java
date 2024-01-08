package dev.gabriel.shared.handlers;

import dev.gabriel.shared.commands.ICommand;
import dev.gabriel.shared.models.AggregateRoot;

public interface ICommandHandler<R extends AggregateRoot, C extends ICommand> extends ICommandHandlerAbstraction {
    R handle(C command);
}

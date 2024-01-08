package dev.gabriel.shared.handlers;

import dev.gabriel.shared.commands.Command;
import dev.gabriel.shared.models.AggregateRoot;

import java.lang.reflect.ParameterizedType;

public interface ICommandHandler<R extends AggregateRoot, C extends Command> {
    R handle(C command);

    Class<C> getCommandType();
}

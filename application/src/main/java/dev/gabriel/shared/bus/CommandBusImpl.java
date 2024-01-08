package dev.gabriel.shared.bus;

import dev.gabriel.shared.commands.Command;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.models.AggregateRoot;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.Map;

public class CommandBusImpl implements ICommandBus {
    private Map<Class<? extends Command>, CommandHandlerProvider> commandHandlers = new HashMap<>();

    public CommandBusImpl(ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanNamesForType(ICommandHandler.class);
        for(String name : names) {
            registerCommand(applicationContext, name);
        }
    }

    @SuppressWarnings("unchecked")
    private void registerCommand(ApplicationContext applicationContext, String name) {
        Class<ICommandHandler<?, ?>> commandHandler = (Class<ICommandHandler<?, ?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(commandHandler, ICommandHandler.class);
        Class<? extends Command> commandType = (Class<? extends Command>) generics[1];
        commandHandlers.put(commandType, new CommandHandlerProvider(applicationContext, commandHandler));
    }

    @SuppressWarnings("unchecked")
    public <R extends AggregateRoot, C extends Command> ICommandHandler<R,C> getHandler(C command) {
        return commandHandlers.get(command.getType()).get();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R extends AggregateRoot, C extends Command> R execute(C command) {
        return (R) getHandler(command).handle(command);
    }
}

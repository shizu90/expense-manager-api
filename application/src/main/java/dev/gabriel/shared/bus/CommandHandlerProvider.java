package dev.gabriel.shared.bus;

import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.context.ApplicationContext;

public class CommandHandlerProvider<H extends ICommandHandler<?, ?>> {
    private final ApplicationContext applicationContext;
    private final Class<H> handlerType;

    public CommandHandlerProvider(ApplicationContext applicationContext, Class<H> handlerType) {
        this.applicationContext = applicationContext;
        this.handlerType = handlerType;
    }

    public H get() {
        return applicationContext.getBean(handlerType);
    }
}

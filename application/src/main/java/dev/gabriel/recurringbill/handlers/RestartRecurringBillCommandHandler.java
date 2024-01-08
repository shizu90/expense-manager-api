package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.RestartRecurringBillCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestartRecurringBillCommandHandler implements ICommandHandler<RecurringBill, RestartRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public RestartRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(RestartRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.restart();

        return recurringBillRepository.save(recurringBill);
    }

    @Override
    public Class<RestartRecurringBillCommand> getCommandType() {
        return RestartRecurringBillCommand.class;
    }
}

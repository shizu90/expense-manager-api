package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.RenameRecurringBillCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenameRecurringBillCommandHandler implements ICommandHandler<RecurringBill, RenameRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public RenameRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(RenameRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .load(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.rename(command.getName());

        return recurringBillRepository.registerEvents(recurringBill);
    }

    @Override
    public Class<RenameRecurringBillCommand> getCommandType() {
        return RenameRecurringBillCommand.class;
    }
}

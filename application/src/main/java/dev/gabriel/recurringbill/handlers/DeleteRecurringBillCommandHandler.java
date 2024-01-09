package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.DeleteRecurringBillCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteRecurringBillCommandHandler implements ICommandHandler<RecurringBill, DeleteRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public DeleteRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(DeleteRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .load(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.delete();

        recurringBillRepository.registerEvents(recurringBill);

        return null;
    }

    @Override
    public Class<DeleteRecurringBillCommand> getCommandType() {
        return DeleteRecurringBillCommand.class;
    }
}

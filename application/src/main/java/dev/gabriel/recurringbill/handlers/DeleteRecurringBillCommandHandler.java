package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.DeleteRecurringBillCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class DeleteRecurringBillCommandHandler implements ICommandHandler<RecurringBill, DeleteRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public DeleteRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(DeleteRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.delete();

        recurringBillRepository.save(recurringBill);

        return null;
    }
}

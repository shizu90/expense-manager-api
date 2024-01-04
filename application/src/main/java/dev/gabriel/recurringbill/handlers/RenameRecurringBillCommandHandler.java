package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.RenameRecurringBillCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class RenameRecurringBillCommandHandler implements ICommandHandler<RecurringBill, RenameRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public RenameRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(RenameRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.rename(command.getName());

        return recurringBillRepository.save(recurringBill);
    }
}

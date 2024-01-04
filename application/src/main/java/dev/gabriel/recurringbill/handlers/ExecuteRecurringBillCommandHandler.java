package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ExecuteRecurringBillCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ExecuteRecurringBillCommandHandler implements ICommandHandler<RecurringBill, ExecuteRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public ExecuteRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(ExecuteRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.execute(command.getNumberOfPeriods());

        return recurringBillRepository.save(recurringBill);
    }
}

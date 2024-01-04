package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.RestartRecurringBillCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class RestartRecurringBillCommandHandler implements ICommandHandler<RecurringBill, RestartRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public RestartRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(RestartRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.restart();

        return recurringBillRepository.save(recurringBill);
    }
}

package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillStartDateCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeRecurringBillStartDateCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillStartDateCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public ChangeRecurringBillStartDateCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(ChangeRecurringBillStartDateCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeStartDate(command.getStartDate());

        return recurringBillRepository.save(recurringBill);
    }
}

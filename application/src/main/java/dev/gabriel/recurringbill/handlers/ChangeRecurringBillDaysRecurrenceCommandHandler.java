package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillDaysRecurrenceCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeRecurringBillDaysRecurrenceCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillDaysRecurrenceCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public ChangeRecurringBillDaysRecurrenceCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(ChangeRecurringBillDaysRecurrenceCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeDaysRecurrence(command.getDaysRecurrence());

        return recurringBillRepository.save(recurringBill);
    }
}

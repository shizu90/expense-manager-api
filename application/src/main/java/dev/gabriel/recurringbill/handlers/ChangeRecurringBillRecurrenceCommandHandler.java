package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillRecurrenceCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class ChangeRecurringBillRecurrenceCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillRecurrenceCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public ChangeRecurringBillRecurrenceCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(ChangeRecurringBillRecurrenceCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeRecurrence(command.getRecurrence());

        return recurringBillRepository.save(recurringBill);
    }
}

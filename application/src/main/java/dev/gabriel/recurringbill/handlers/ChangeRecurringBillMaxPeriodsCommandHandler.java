package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillMaxPeriodsCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class ChangeRecurringBillMaxPeriodsCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillMaxPeriodsCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public ChangeRecurringBillMaxPeriodsCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(ChangeRecurringBillMaxPeriodsCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeMaxPeriods(command.getMaxPeriods());

        return recurringBillRepository.save(recurringBill);
    }
}

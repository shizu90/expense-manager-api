package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillTotalPeriodsCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeRecurringBillTotalPeriodsCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillTotalPeriodsCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public ChangeRecurringBillTotalPeriodsCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(ChangeRecurringBillTotalPeriodsCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeTotalPeriods(command.getTotalPeriods());

        return recurringBillRepository.save(recurringBill);
    }
}

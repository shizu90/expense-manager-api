package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillAmountCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeRecurringBillAmountCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillAmountCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public ChangeRecurringBillAmountCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(ChangeRecurringBillAmountCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeAmount(command.getAmount());

        return recurringBillRepository.save(recurringBill);
    }
}

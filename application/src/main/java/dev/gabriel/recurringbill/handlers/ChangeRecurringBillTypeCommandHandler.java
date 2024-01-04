package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillTypeCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeRecurringBillTypeCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillTypeCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public ChangeRecurringBillTypeCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(ChangeRecurringBillTypeCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeType(RecurringBillType.getConstant(command.getType()));

        return recurringBillRepository.save(recurringBill);
    }
}

package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillTypeCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeRecurringBillTypeCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillTypeCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public ChangeRecurringBillTypeCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(ChangeRecurringBillTypeCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .load(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeType(RecurringBillType.getConstant(command.getType()));

        return recurringBillRepository.registerEvents(recurringBill);
    }

    @Override
    public Class<ChangeRecurringBillTypeCommand> getCommandType() {
        return ChangeRecurringBillTypeCommand.class;
    }
}

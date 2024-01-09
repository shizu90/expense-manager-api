package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillMaxPeriodsCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeRecurringBillMaxPeriodsCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillMaxPeriodsCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public ChangeRecurringBillMaxPeriodsCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(ChangeRecurringBillMaxPeriodsCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .load(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeMaxPeriods(command.getMaxPeriods());

        return recurringBillRepository.registerEvents(recurringBill);
    }

    @Override
    public Class<ChangeRecurringBillMaxPeriodsCommand> getCommandType() {
        return ChangeRecurringBillMaxPeriodsCommand.class;
    }
}

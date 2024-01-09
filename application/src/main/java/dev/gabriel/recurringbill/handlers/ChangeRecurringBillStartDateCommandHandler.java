package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillStartDateCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeRecurringBillStartDateCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillStartDateCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public ChangeRecurringBillStartDateCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(ChangeRecurringBillStartDateCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .load(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeStartDate(command.getStartDate());

        return recurringBillRepository.registerEvents(recurringBill);
    }

    @Override
    public Class<ChangeRecurringBillStartDateCommand> getCommandType() {
        return ChangeRecurringBillStartDateCommand.class;
    }
}

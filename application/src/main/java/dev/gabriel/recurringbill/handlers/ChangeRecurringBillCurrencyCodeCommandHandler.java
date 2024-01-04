package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ChangeRecurringBillCurrencyCodeCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;

public class ChangeRecurringBillCurrencyCodeCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillCurrencyCodeCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    public ChangeRecurringBillCurrencyCodeCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill execute(ChangeRecurringBillCurrencyCodeCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.changeCurrencyCode(CurrencyCode.getConstant(command.getCurrencyCode()));

        return recurringBillRepository.save(recurringBill);
    }
}

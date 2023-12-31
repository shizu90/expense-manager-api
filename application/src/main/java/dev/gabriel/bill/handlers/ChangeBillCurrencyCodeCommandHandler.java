package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.ChangeBillCurrencyCodeCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeBillCurrencyCodeCommandHandler implements ICommandHandler<Bill, ChangeBillCurrencyCodeCommand> {
    private final IBillRepository billRepository;

    public ChangeBillCurrencyCodeCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill execute(ChangeBillCurrencyCodeCommand command) {
        Bill bill = billRepository.findById(command.getBillId()).orElseThrow(() -> new BillNotFoundException(command.getBillId().getValue()));
        bill.changeCurrencyCode(command.getCurrencyCode());

        return billRepository.save(bill);
    }
}

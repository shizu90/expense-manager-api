package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.ChangeBillAmountCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeBillAmountCommandHandler implements ICommandHandler<Bill, ChangeBillAmountCommand> {
    private final IBillRepository billRepository;

    public ChangeBillAmountCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill execute(ChangeBillAmountCommand command) {
        Bill bill = billRepository.findById(command.getBillId()).orElseThrow(() -> new BillNotFoundException(command.getBillId().getValue()));
        bill.changeAmount(command.getAmount());

        return billRepository.save(bill);
    }
}

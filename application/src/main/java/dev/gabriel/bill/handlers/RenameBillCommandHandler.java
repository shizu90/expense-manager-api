package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.RenameBillCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class RenameBillCommandHandler implements ICommandHandler<Bill, RenameBillCommand> {
    private final IBillRepository billRepository;

    public RenameBillCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill execute(RenameBillCommand command) {
        Bill bill = billRepository.findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        bill.rename(command.getName());

        return billRepository.save(bill);
    }
}

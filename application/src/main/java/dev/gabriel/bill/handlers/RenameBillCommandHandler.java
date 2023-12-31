package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.RenameBillCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.shared.handlers.ICommandHandler;

import java.util.Optional;

public class RenameBillCommandHandler implements ICommandHandler<Bill, RenameBillCommand> {
    private final IBillRepository billRepository;

    public RenameBillCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill execute(RenameBillCommand command) {
        Bill bill = billRepository.findById(command.getBillId()).orElseThrow(() -> new BillNotFoundException(command.getBillId().getValue()));
        bill.rename(command.getName());

        return billRepository.save(bill);
    }
}

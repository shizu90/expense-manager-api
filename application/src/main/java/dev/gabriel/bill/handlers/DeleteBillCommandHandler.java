package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.DeleteBillCommand;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.shared.handlers.ICommandHandler;

public class DeleteBillCommandHandler implements ICommandHandler<Bill, DeleteBillCommand> {
    private final IBillRepository billRepository;

    public DeleteBillCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill execute(DeleteBillCommand command) {
        billRepository.deleteById(command.getBillId());
        return null;
    }
}

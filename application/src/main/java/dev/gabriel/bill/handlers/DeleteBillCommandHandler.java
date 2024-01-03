package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.DeleteBillCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class DeleteBillCommandHandler implements ICommandHandler<Bill, DeleteBillCommand> {
    private final IBillRepository billRepository;

    public DeleteBillCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill execute(DeleteBillCommand command) {
        Bill bill = billRepository.findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        bill.delete();

        billRepository.save(bill);

        return null;
    }
}

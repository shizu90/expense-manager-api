package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.EditBillCommentCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.shared.handlers.ICommandHandler;

public class EditBillCommentCommandHandler implements ICommandHandler<Bill, EditBillCommentCommand> {
    private final IBillRepository billRepository;

    public EditBillCommentCommand(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill execute(EditBillCommentCommand command) {
        Bill bill = billRepository.findById(command.getBillId()).orElseThrow(() -> new BillNotFoundException(command.getBillId().getValue()));
        bill.editComment(command.getComment());

        return billRepository.save(bill);
    }
}

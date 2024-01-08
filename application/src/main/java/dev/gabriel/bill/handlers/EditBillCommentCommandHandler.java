package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.EditBillCommentCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class EditBillCommentCommandHandler implements ICommandHandler<Bill, EditBillCommentCommand> {
    private final IBillRepository billRepository;

    @Autowired
    public EditBillCommentCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill handle(EditBillCommentCommand command) {
        Bill bill = billRepository.findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        bill.editComment(command.getComment());

        return billRepository.save(bill);
    }
}

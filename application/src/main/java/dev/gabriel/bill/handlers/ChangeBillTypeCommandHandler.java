package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.ChangeBillTypeCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeBillTypeCommandHandler implements ICommandHandler<Bill, ChangeBillTypeCommand> {
    private final IBillRepository billRepository;

    public ChangeBillTypeCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill execute(ChangeBillTypeCommand command) {
        Bill bill = billRepository
                .findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        bill.changeType(BillType.getConstant(command.getType()));

        return billRepository.save(bill);
    }
}

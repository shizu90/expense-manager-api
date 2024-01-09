package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.RenameBillCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenameBillCommandHandler implements ICommandHandler<Bill, RenameBillCommand> {
    private final IBillRepository billRepository;

    @Autowired
    public RenameBillCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill handle(RenameBillCommand command) {
        Bill bill = billRepository
                .load(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        bill.rename(command.getName());

        return billRepository.registerEvents(bill);
    }

    @Override
    public Class<RenameBillCommand> getCommandType() {
        return RenameBillCommand.class;
    }
}

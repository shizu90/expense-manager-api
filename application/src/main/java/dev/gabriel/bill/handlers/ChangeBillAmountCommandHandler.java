package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.ChangeBillAmountCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeBillAmountCommandHandler implements ICommandHandler<Bill, ChangeBillAmountCommand> {
    private final IBillRepository billRepository;

    @Autowired
    public ChangeBillAmountCommandHandler(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill handle(ChangeBillAmountCommand command) {
        Bill bill = billRepository
                .findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        bill.changeAmount(command.getAmount());

        return billRepository.save(bill);
    }

    @Override
    public Class<ChangeBillAmountCommand> getCommandType() {
        return ChangeBillAmountCommand.class;
    }
}

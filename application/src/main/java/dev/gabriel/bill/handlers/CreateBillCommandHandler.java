package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.CreateBillCommand;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillStatus;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.repositories.IUserRepository;

import java.util.UUID;

public class CreateBillCommandHandler implements ICommandHandler<Bill, CreateBillCommand> {
    private final IBillRepository billRepository;
    private final IUserRepository userRepository;

    public CreateBillCommandHandler(IBillRepository billRepository, IUserRepository userRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bill execute(CreateBillCommand command) {
        if(!userRepository.exists(command.getUserId())) throw new UserNotFoundException(command.getUserId().getValue());

        Bill bill = Bill.create(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getComment(),
                Currency.create(command.getAmount(), command.getCurrencyCode()),
                BillStatus.UNPAID,
                command.getType(),
                command.getUserId(),
                command.getRecurringBillId()
        );

        return billRepository.save(bill);
    }
}

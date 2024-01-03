package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.CreateBillCommand;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillStatus;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.UUID;

public class CreateBillCommandHandler implements ICommandHandler<Bill, CreateBillCommand> {
    private final IBillRepository billRepository;
    private final IUserRepository userRepository;
    private final IRecurringBillRepository recurringBillRepository;
    private final ICategoryRepository categoryRepository;

    public CreateBillCommandHandler(IBillRepository billRepository,
                                    IUserRepository userRepository,
                                    IRecurringBillRepository recurringBillRepository,
                                    ICategoryRepository categoryRepository
    ) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
        this.recurringBillRepository = recurringBillRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Bill execute(CreateBillCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));

        Category category = categoryRepository
                .findById(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));

        RecurringBill recurrence = null;
        if(command.getRecurrenceId() != null)
            recurrence = recurringBillRepository
                    .findById(RecurringBillId.create(command.getRecurrenceId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurrenceId()));

        Bill bill = Bill.create(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getComment(),
                command.getAmount(),
                CurrencyCode.valueOf(command.getCurrencyCode()),
                BillStatus.valueOf(command.getStatus()),
                BillType.valueOf(command.getType()),
                user.getId(),
                category.getId(),
                recurrence != null ? recurrence.getId() : null
        );

        return billRepository.save(bill);
    }
}

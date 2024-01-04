package dev.gabriel.recurringbill.handlers;

import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.commands.CreateRecurringBillCommand;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.util.UUID;

public class CreateRecurringBillCommandHandler implements ICommandHandler<RecurringBill, CreateRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;
    private final IWalletRepository walletRepository;
    private final IReminderRepository reminderRepository;
    private final ICategoryRepository categoryRepository;

    public CreateRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository,
                                             IWalletRepository walletRepository,
                                             IReminderRepository reminderRepository,
                                             ICategoryRepository categoryRepository
    ) {
        this.recurringBillRepository = recurringBillRepository;
        this.walletRepository = walletRepository;
        this.reminderRepository = reminderRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public RecurringBill execute(CreateRecurringBillCommand command) {
        Wallet wallet = walletRepository
                .findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));

        Category category = categoryRepository
                .findById(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));

        RecurringBill recurringBill = RecurringBill.create(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getComment(),
                command.getAmount(),
                CurrencyCode.getConstant(command.getCurrencyCode()),
                category.getId(),
                RecurringBillType.getConstant(command.getType()),
                wallet.getId(),
                command.getDaysRecurrence(),
                command.getTotalPeriods(),
                command.getStartDate()
        );

        Reminder reminder = Reminder.create(
                UUID.randomUUID().toString(),
                command.getName() + " reminder",
                command.getComment(),
                command.getDaysRecurrence(),
                command.getTotalPeriods(),
                wallet.getUserId()
        );

        recurringBill.setupReminder(reminder.getId());

        reminder.start();

        reminderRepository.save(reminder);

        return recurringBillRepository.save(recurringBill);
    }
}

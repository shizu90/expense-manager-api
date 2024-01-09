package dev.gabriel.recurringbill.handlers;

import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.commands.CreateRecurringBillCommand;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.reminder.exceptions.ReminderNotFoundException;
import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.services.CurrencyConversionService;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class CreateRecurringBillCommandHandler implements ICommandHandler<RecurringBill, CreateRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;
    private final IWalletRepository walletRepository;
    private final ICategoryRepository categoryRepository;
    private final IReminderRepository reminderRepository;
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public CreateRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository,
                                             IWalletRepository walletRepository,
                                             ICategoryRepository categoryRepository,
                                             IReminderRepository reminderRepository,
                                             CurrencyConversionService currencyConversionService
    ) {
        this.recurringBillRepository = recurringBillRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
        this.reminderRepository = reminderRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    public RecurringBill handle(CreateRecurringBillCommand command) {
        Wallet wallet = walletRepository
                .load(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));

        Category category = categoryRepository
                .load(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));
        Reminder reminder = reminderRepository
                .load(ReminderId.create(command.getReminderId())).orElseThrow(() -> new ReminderNotFoundException(command.getReminderId()));

        RecurringBill recurringBill = RecurringBill.create(
                UUID.randomUUID(),
                command.getName(),
                command.getComment(),
                command.getAmount(),
                CurrencyCode.getConstant(command.getCurrencyCode()),
                category.getId(),
                RecurringBillType.getConstant(command.getType()),
                wallet.getId(),
                reminder.getId(),
                command.getRecurrence(),
                command.getMaxPeriods(),
                command.getStartDate()
        );

        if(recurringBill.getStartDate().equals(LocalDate.now())) {
            BigDecimal amountToDiscount = recurringBill.getAmount().getValue();

            if(!recurringBill.getAmount().getCurrencyCode().equals(wallet.getBalance().getCurrencyCode())) {
                amountToDiscount = currencyConversionService.convert(recurringBill.getAmount(), wallet.getBalance().getCurrencyCode());
            }

            if(recurringBill.getType().equals(RecurringBillType.EXPENSE)) {
                wallet.updateBalance(wallet.getBalance()
                        .subtract(Currency.create(amountToDiscount, wallet.getBalance().getCurrencyCode())).getValue());
            }else {
                wallet.updateBalance(wallet.getBalance()
                        .add(Currency.create(amountToDiscount, wallet.getBalance().getCurrencyCode())).getValue());
            }

            walletRepository.registerEvents(wallet);
        }

        recurringBill = recurringBillRepository.registerEvents(recurringBill);

        return recurringBill;
    }

    @Override
    public Class<CreateRecurringBillCommand> getCommandType() {
        return CreateRecurringBillCommand.class;
    }
}

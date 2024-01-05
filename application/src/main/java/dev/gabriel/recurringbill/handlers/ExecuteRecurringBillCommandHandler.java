package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.ExecuteRecurringBillCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.models.RecurringBillType;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;

public class ExecuteRecurringBillCommandHandler implements ICommandHandler<RecurringBill, ExecuteRecurringBillCommand> {
    private final IRecurringBillRepository recurringBillRepository;
    private final IWalletRepository walletRepository;

    public ExecuteRecurringBillCommandHandler(IRecurringBillRepository recurringBillRepository, IWalletRepository walletRepository) {
        this.recurringBillRepository = recurringBillRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public RecurringBill execute(ExecuteRecurringBillCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        Wallet wallet = walletRepository
                .findById(recurringBill.getWalletId()).orElseThrow(() -> new WalletNotFoundException(recurringBill.getWalletId().getValue()));

        recurringBill.execute(command.getNumberOfPeriods());

        if(recurringBill.getType().equals(RecurringBillType.EXPENSE)) {
            wallet.updateBalance(wallet.getBalance().subtract(recurringBill.getAmount().multiply(command.getNumberOfPeriods())).getValue());
        }else wallet.updateBalance(wallet.getBalance().add(recurringBill.getAmount().multiply(command.getNumberOfPeriods())).getValue());

        walletRepository.save(wallet);
        recurringBillRepository.save(recurringBill);

        return recurringBill;
    }
}

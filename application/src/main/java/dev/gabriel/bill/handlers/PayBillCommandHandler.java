package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.PayBillCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.transaction.models.Transaction;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.util.UUID;

public class PayBillCommandHandler implements ICommandHandler<Transaction, PayBillCommand> {
    private final IBillRepository billRepository;
    private final IWalletRepository walletRepository;
    private final IRecurringBillRepository recurringBillRepository;

    public PayBillCommandHandler(
            IBillRepository billRepository,
            IWalletRepository walletRepository,
            IRecurringBillRepository recurringBillRepository
    ) {
        this.billRepository = billRepository;
        this.walletRepository = walletRepository;
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public Transaction execute(PayBillCommand command) {
        Bill bill = billRepository.findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        Wallet wallet = walletRepository.findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));

        RecurringBill recurrence = null;
        if(bill.getRecurrenceId() != null)
             recurrence = recurringBillRepository
                .findById(bill.getRecurrenceId()).orElseThrow(() -> new RecurringBillNotFoundException(bill.getRecurrenceId().getValue()));

        long periods = command.getNumberOfPeriods() != null ? command.getNumberOfPeriods() : 1L;
        Currency newWalletBalance = wallet.getBalance();
        Currency amountOfBill = bill.pay().multiply(periods);

        if(bill.getType().equals(BillType.IN)) {
            newWalletBalance = newWalletBalance.add(amountOfBill);
        }else {
            newWalletBalance = newWalletBalance.subtract(amountOfBill);
        }

        if(recurrence != null) {
            recurrence.payPeriods(periods);
        }

        wallet.updateBalance(newWalletBalance.getValue());

        billRepository.save(bill);
        recurringBillRepository.save(recurrence);
        walletRepository.save(wallet);

        return Transaction.create(UUID.randomUUID().toString(), wallet.getId(), bill.getId(), amountOfBill);
    }
}

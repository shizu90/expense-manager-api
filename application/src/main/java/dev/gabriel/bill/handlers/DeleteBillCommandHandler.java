package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.DeleteBillCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.services.CurrencyConversionService;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;

import java.math.BigDecimal;

public class DeleteBillCommandHandler implements ICommandHandler<Bill, DeleteBillCommand> {
    private final IBillRepository billRepository;
    private final IWalletRepository walletRepository;
    private final CurrencyConversionService currencyConversionService;

    public DeleteBillCommandHandler(IBillRepository billRepository,
                                    IWalletRepository walletRepository,
                                    CurrencyConversionService currencyConversionService
    ) {
        this.billRepository = billRepository;
        this.walletRepository = walletRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    public Bill execute(DeleteBillCommand command) {
        Bill bill = billRepository.findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        Wallet wallet = walletRepository.findById(bill.getWalletId()).orElseThrow(() -> new WalletNotFoundException(bill.getWalletId().getValue()));

        BigDecimal amountToDiscount = bill.getAmount().getValue();

        if(!bill.getAmount().getCurrencyCode().equals(wallet.getBalance().getCurrencyCode())) {
            amountToDiscount = currencyConversionService.convert(bill.getAmount(), wallet.getBalance().getCurrencyCode());
        }

        if(bill.getType().equals(BillType.EXPENSE)) {
            wallet.updateBalance(wallet.getBalance()
                    .add(Currency.create(amountToDiscount, wallet.getBalance().getCurrencyCode())).getValue());
        }else {
            wallet.updateBalance(wallet.getBalance()
                    .subtract(Currency.create(amountToDiscount, wallet.getBalance().getCurrencyCode())).getValue());
        }

        bill.delete();

        billRepository.save(bill);
        walletRepository.save(wallet);

        return null;
    }
}

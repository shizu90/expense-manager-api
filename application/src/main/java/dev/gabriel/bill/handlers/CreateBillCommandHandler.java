package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.CreateBillCommand;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
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
import java.util.UUID;

@Component
public class CreateBillCommandHandler implements ICommandHandler<Bill, CreateBillCommand> {
    private final IBillRepository billRepository;
    private final IWalletRepository walletRepository;
    private final ICategoryRepository categoryRepository;
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public CreateBillCommandHandler(IBillRepository billRepository,
                                    IWalletRepository walletRepository,
                                    ICategoryRepository categoryRepository,
                                    CurrencyConversionService currencyConversionService
    ) {
        this.billRepository = billRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
        public Bill handle(CreateBillCommand command) {
        Wallet wallet = walletRepository
                .load(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        Category category = categoryRepository
                .load(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));

        Bill bill = Bill.create(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getComment(),
                command.getAmount(),
                CurrencyCode.getConstant(command.getCurrencyCode()),
                BillType.getConstant(command.getType()),
                wallet.getId(),
                category.getId()
        );

        BigDecimal amountToDiscount = bill.getAmount().getValue();

        if(!bill.getAmount().getCurrencyCode().equals(wallet.getBalance().getCurrencyCode())) {
            amountToDiscount = currencyConversionService.convert(bill.getAmount(), wallet.getBalance().getCurrencyCode());
        }

        if(bill.getType().equals(BillType.EXPENSE)) {
            wallet.updateBalance(wallet.getBalance()
                    .subtract(Currency.create(amountToDiscount, wallet.getBalance().getCurrencyCode())).getValue());
        }else {
            wallet.updateBalance(wallet.getBalance()
                    .add(Currency.create(amountToDiscount, wallet.getBalance().getCurrencyCode())).getValue());
        }

        walletRepository.registerEvents(wallet);
        billRepository.registerEvents(bill);

        return bill;
    }

    @Override
    public Class<CreateBillCommand> getCommandType() {
        return CreateBillCommand.class;
    }
}

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
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.util.UUID;

public class CreateBillCommandHandler implements ICommandHandler<Bill, CreateBillCommand> {
    private final IBillRepository billRepository;
    private final IWalletRepository walletRepository;
    private final ICategoryRepository categoryRepository;

    public CreateBillCommandHandler(IBillRepository billRepository,
                                    IWalletRepository walletRepository,
                                    ICategoryRepository categoryRepository
    ) {
        this.billRepository = billRepository;
        this.walletRepository = walletRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Bill execute(CreateBillCommand command) {
        Wallet wallet = walletRepository.findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        Category category = categoryRepository.findById(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));

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

        if(bill.getType().equals(BillType.EXPENSE)) {
            wallet.updateBalance(wallet.getBalance().subtract(bill.getAmount()).getValue());
        }else {
            wallet.updateBalance(wallet.getBalance().add(bill.getAmount()).getValue());
        }

        walletRepository.save(wallet);
        billRepository.save(bill);

        return bill;
    }
}

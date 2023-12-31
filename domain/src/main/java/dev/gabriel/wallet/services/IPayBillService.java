package dev.gabriel.wallet.services;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.services.IDomainService;
import dev.gabriel.transaction.models.Transaction;
import dev.gabriel.wallet.valueobjects.WalletId;

public interface IPayBillService extends IDomainService {
    Transaction pay(BillId billId, WalletId walletId);
}

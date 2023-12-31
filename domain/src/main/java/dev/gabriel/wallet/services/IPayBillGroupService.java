package dev.gabriel.wallet.services;

import dev.gabriel.billgroup.valueobjects.BillGroupId;
import dev.gabriel.shared.services.IDomainService;
import dev.gabriel.transaction.models.Transaction;
import dev.gabriel.wallet.valueobjects.WalletId;

public interface IPayBillGroupService extends IDomainService {
    Transaction pay(BillGroupId groupId, WalletId walletId);
}

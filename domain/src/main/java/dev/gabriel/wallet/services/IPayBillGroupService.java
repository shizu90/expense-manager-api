package dev.gabriel.wallet.services;

import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.services.IDomainService;
import dev.gabriel.transaction.models.Transaction;
import dev.gabriel.wallet.valueobjects.WalletId;

public interface IPayBillGroupService extends IDomainService {
    Transaction pay(BudgetId groupId, WalletId walletId);
}

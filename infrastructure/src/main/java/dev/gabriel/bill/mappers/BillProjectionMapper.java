package dev.gabriel.bill.mappers;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.projection.BillProjection;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.mappers.IProjectionMapper;
import dev.gabriel.wallet.valueobjects.WalletId;

public class BillProjectionMapper implements IProjectionMapper<BillProjection, Bill> {
    @Override
    public Bill toDomain(BillProjection projection) {
        return Bill.create(
                projection.getId(),
                projection.getName(),
                projection.getComment(),
                projection.getAmount(),
                projection.getCurrencyCode(),
                projection.getType(),
                WalletId.create(projection.getWallet().getId()),
                CategoryId.create(projection.getCategory().getId())
        );
    }
}

package dev.gabriel.bill.repositories;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface IBillRepository extends IDomainRepository {
    Optional<Bill> findById(BillId billId);
    List<Bill> findByUserId(UserId userId);
    List<Bill> findByUserIdAndType(UserId userId, BillType type);
    Bill save(Bill bill);
    void deleteById(BillId userId);
}

package dev.gabriel.bill.repositories;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillStatus;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface IBillRepository extends IDomainRepository {
    Optional<Bill> findById(BillId billId);
    List<Bill> findByUserId(UserId userId);
    List<Bill> findByUserIdAndStatus(UserId userId, BillStatus status);
    List<Bill> findByUserIdAndType(UserId userId, BillType type);
    List<Bill> findByUserIdAndTypeAndStatus(UserId userId, BillType type, BillStatus status);
    List<Bill> findByUserIdAndRecurringBillId(UserId userId, RecurringBillId recurringBillId);
    Bill save(Bill bill);
    void deleteById(UserId userId);
}

package dev.gabriel.bill.repositories;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillStatus;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;
import java.util.Optional;

public interface IBillRepository extends IDomainRepository {
    Optional<Bill> findById(String id);
    List<Bill> findByUserId(String userId);
    List<Bill> findByUserIdAndStatus(String userId, BillStatus status);
    List<Bill> findByUserIdAndType(String userId, BillType type);
    Bill save(Bill bill);
    void deleteById(String id);
}

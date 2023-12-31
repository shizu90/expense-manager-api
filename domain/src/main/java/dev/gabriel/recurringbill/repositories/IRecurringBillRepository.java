package dev.gabriel.recurringbill.repositories;

import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface IRecurringBillRepository extends IDomainRepository {
    Optional<RecurringBill> findById(RecurringBillId recurringBillId);
    List<RecurringBill> findByUserId(UserId userId);
    RecurringBill save(RecurringBill recurringBill);
    void deleteById(RecurringBillId recurringBillId);
}

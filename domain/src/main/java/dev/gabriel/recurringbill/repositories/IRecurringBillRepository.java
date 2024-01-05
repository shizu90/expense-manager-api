package dev.gabriel.recurringbill.repositories;

import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.Optional;

public interface IRecurringBillRepository extends IDomainRepository {
    Optional<RecurringBill> findById(RecurringBillId recurringBillId);
    RecurringBill save(RecurringBill recurringBill);
}

package dev.gabriel.bill.repositories;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.Optional;

public interface IBillRepository extends IDomainRepository {
    Optional<Bill> load(BillId billId);
    Bill registerEvents(Bill bill);
}

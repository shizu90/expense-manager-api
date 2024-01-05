package dev.gabriel.bill.eventstore;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;

import java.util.Optional;

public class BillEventStore implements IBillRepository {
    @Override
    public Optional<Bill> findById(BillId billId) {
        return Optional.empty();
    }

    @Override
    public Bill save(Bill bill) {
        return null;
    }
}

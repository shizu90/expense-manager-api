package dev.gabriel.recurringbill.eventstore;

import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

import java.util.Optional;

public class RecurringBillEventStore implements IRecurringBillRepository {
    @Override
    public Optional<RecurringBill> load(RecurringBillId recurringBillId) {
        return Optional.empty();
    }

    @Override
    public RecurringBill registerEvents(RecurringBill recurringBill) {
        return null;
    }
}

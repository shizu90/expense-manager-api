package dev.gabriel.budget.eventstore;

import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.repositories.IBudgetRepository;
import dev.gabriel.budget.valueobjects.BudgetId;

import java.util.Optional;

public class BudgetEventStore implements IBudgetRepository {
    @Override
    public Optional<Budget> findById(BudgetId budgetId) {
        return Optional.empty();
    }

    @Override
    public Budget save(Budget budget) {
        return null;
    }
}

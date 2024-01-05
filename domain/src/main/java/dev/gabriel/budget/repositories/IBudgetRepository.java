package dev.gabriel.budget.repositories;

import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.Optional;

public interface IBudgetRepository extends IDomainRepository {
    Optional<Budget> findById(BudgetId budgetId);
    Budget save(Budget budget);
}

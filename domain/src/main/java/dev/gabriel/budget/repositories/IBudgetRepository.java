package dev.gabriel.budget.repositories;

import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface IBudgetRepository extends IDomainRepository {
    Optional<Budget> findById(BudgetId budgetId);
    List<Budget> findByUserId(UserId userId);
    Budget save(Budget budget);
    void deleteById(BudgetId budgetId);
}

package dev.gabriel.budget.mappers;

import dev.gabriel.budget.models.Budget;
import dev.gabriel.budget.projection.BudgetItemProjection;
import dev.gabriel.budget.projection.BudgetProjection;
import dev.gabriel.shared.mappers.IProjectionMapper;
import dev.gabriel.user.valueobjects.UserId;

public class BudgetProjectionMapper implements IProjectionMapper<BudgetProjection, Budget> {
    @Override
    public Budget toDomain(BudgetProjection projection) {
        Budget budget = Budget.create(
                projection.getId(),
                projection.getName(),
                projection.getDescription(),
                projection.getCurrencyCode(),
                UserId.create(projection.getOwner().getId())
        );

        for(BudgetItemProjection budgetItem : projection.getBills()) {
            budget.addBill();
        }
    }
}

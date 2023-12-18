package dev.gabriel.entities.wallet.incomes;

import dev.gabriel.entities.wallet.IRecurringBill;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.primitives.Entity;
import dev.gabriel.valueobjects.Money;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class IncomeList extends Entity implements IIncomeList {
    protected final List<Income> content;

    private IncomeList(String id) {
        super(id);
        content = new ArrayList<>();
    }

    public static IncomeList create(String id) {
        return new IncomeList(id);
    }
    @Override
    public void createCommonIncome(String id, String name, String comment, Money amount, IncomeCategory category) {
        CommonIncome commonIncome = CommonIncome.create(id, name, comment, amount, category);
        content.add(commonIncome);
    }
    @Override
    public void createRecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence) {
        RecurringIncome recurringIncome = RecurringIncome.create(id, name, comment, amount, category, daysOccurrence);
        content.add(recurringIncome);
    }
    @Override
    public void createRecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, Date startDate) {
        RecurringIncome recurringIncome = RecurringIncome.create(id, name, comment, amount, category, daysOccurrence, startDate);
        content.add(recurringIncome);
    }
    @Override
    public void removeIncome(Income income) {
        content.remove(income);
    }
    @Override
    public List<CommonIncome> getCommonIncomes() {
        return content.stream()
                .filter(e -> !(e instanceof IRecurringBill))
                .map(e -> (CommonIncome) e)
                .collect(Collectors.toList());
    }
    @Override
    public List<RecurringIncome> getRecurringIncomes() {
        return content.stream()
                .filter(e -> e instanceof IRecurringBill)
                .map(e -> (RecurringIncome) e)
                .collect(Collectors.toList());
    }
    @Override
    public CommonIncome getCommonIncomeById(String commonIncomeId) {
        return getCommonIncomes()
                .stream().filter(ci -> ci.getIdentity().equals(commonIncomeId))
                .findFirst().orElseThrow(() -> new NotFoundException("Common income not found."));
    }
    @Override
    public RecurringIncome getRecurringIncomeById(String recurringIncomeId) {
        return getRecurringIncomes()
                .stream().filter(ri -> ri.getIdentity().equals(recurringIncomeId))
                .findFirst().orElseThrow(() -> new NotFoundException("Recurring income not found."));
    }
}

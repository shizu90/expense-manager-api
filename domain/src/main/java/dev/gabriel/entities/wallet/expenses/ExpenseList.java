package dev.gabriel.entities.wallet.expenses;

import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.entities.wallet.IRecurringBill;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.primitives.Entity;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseList extends Entity implements IExpenseList {
    protected final List<Expense> content;

    private ExpenseList(String id) {
        super(id);
        content = new ArrayList<>();
    }

    public static ExpenseList create(String id) {
        return new ExpenseList(id);
    }

    @Override
    public void createCommonExpense(String id, String name, String comment, Money amount, ExpenseCategory category) {
        CommonExpense commonExpense = CommonExpense.create(id, name, comment, amount, category);
        content.add(commonExpense);
    }
    @Override
    public void createRecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence) {
        RecurringExpense recurringExpense = RecurringExpense.create(id, name, comment, amount, category, daysOccurrence);
        content.add(recurringExpense);
    }
    @Override
    public void createRecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, Date startDate) {
        RecurringExpense recurringExpense = RecurringExpense.create(id, name, comment, amount, category, daysOccurrence, startDate);
        content.add(recurringExpense);
    }
    @Override
    public void removeExpense(Expense expense) {
        content.remove(expense);
    }
    @Override
    public List<CommonExpense> getCommonExpenses() {
        return content.stream()
                .filter(e -> !(e instanceof IRecurringBill))
                .map(e -> (CommonExpense) e)
                .collect(Collectors.toList());
    }
    @Override
    public List<RecurringExpense> getRecurringExpenses() {
        return content.stream()
                .filter(e -> e instanceof IRecurringBill)
                .map(e -> (RecurringExpense) e)
                .collect(Collectors.toList());
    }
    @Override
    public CommonExpense getCommonExpenseById(String commonExpenseId) {
        return getCommonExpenses()
                .stream().filter(ce -> ce.getIdentity().equals(commonExpenseId))
                .findFirst().orElseThrow(() -> new NotFoundException("Common expense not found."));
    }

    @Override
    public RecurringExpense getRecurringExpenseById(String recurringExpenseId) {
        return getRecurringExpenses()
                .stream().filter(re -> re.getIdentity().equals(recurringExpenseId))
                .findFirst().orElseThrow(() -> new NotFoundException("Recurring expense not found."));
    }
}

package dev.gabriel.entities;

import dev.gabriel.primitives.AggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Wallet extends AggregateRoot {
    private String name;
    private BigDecimal balance;
    private List<Expense> expenses;
    private List<Income> incomes;

    private Wallet(Long id, String name, Double balance) {
        super(id);
        this.name = name;
        this.balance = BigDecimal.valueOf(balance);
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
    }

    public static Wallet create(Long id, Double balance, String name) {
        return new Wallet(id, name, balance);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void addIncome(Income income) {
        incomes.add(income);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }

    public void removeIncome(Income income) {
        incomes.remove(income);
    }

    public List<RecurringExpense> getRecurringExpenses() {
        return expenses.stream()
                .filter(e -> e instanceof IRecurringBill)
                .map(e -> (RecurringExpense) e)
                .collect(Collectors.toList());
    }

    public List<RecurringIncome> getRecurringIncomes() {
        return incomes.stream()
                .filter(e -> e instanceof IRecurringBill)
                .map(e -> (RecurringIncome) e)
                .collect(Collectors.toList());
    }

    public List<CommonExpense> getCommonExpenses() {
        return expenses.stream()
                .filter(e -> !(e instanceof IRecurringBill))
                .map(e -> (CommonExpense) e)
                .collect(Collectors.toList());
    }

    public List<CommonIncome> getCommonIncomes() {
        return incomes.stream()
                .filter(e -> !(e instanceof IRecurringBill))
                .map(e -> (CommonIncome) e)
                .collect(Collectors.toList());
    }

    public BigDecimal calculateBalance() {
        return balance;
    }
}

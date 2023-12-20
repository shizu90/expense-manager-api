package dev.gabriel.wallet.entities;

import dev.gabriel.bill.entities.expense.CommonExpense;
import dev.gabriel.bill.entities.expense.RecurringExpense;
import dev.gabriel.bill.entities.income.CommonIncome;
import dev.gabriel.bill.entities.income.RecurringIncome;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.shared.exceptions.InsufficientFundsException;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Wallet extends AggregateRoot {
    private String name;
    private String comment;
    private Money balance;
    private Money initialBalance;
    private Identity userId;
    private LocalDate lastBalanceUpdate;

    private Wallet(String id, String name, String comment, Money balance, Identity userId) {
        super(id);
        this.name = name;
        this.comment = comment;
        this.balance = balance;
        this.initialBalance = balance;
        this.userId = userId;
        this.lastBalanceUpdate = getCreatedAt();
    }

    public static Wallet create(String id, Money balance, String name, String comment, Identity userId) {
        return new Wallet(id, name, comment, balance, userId);
    }

    public void rename(String name) {
        this.name = name;
    }

    public void changeComment(String comment) {
        this.comment = comment;
    }

    public void addAmount(Money amount) {
        balance = balance.add(amount);
    }

    public void payCommonExpense(CommonExpense commonExpense) {
        Money commonExpenseAmount = commonExpense.getAmount();

        if(!balance.isGreater(commonExpenseAmount)) {
            throw new InsufficientFundsException("Cant pay that expense with current wallet.");
        }

        commonExpense.updateStatus(BillStatus.PAID);
        balance = balance.subtract(commonExpense.getAmount());
        lastBalanceUpdate = LocalDate.now();
    }

    public void receiveCommonIncomePayment(CommonIncome commonIncome) {
        balance.add(commonIncome.getAmount());
        commonIncome.updateStatus(BillStatus.PAID);
        lastBalanceUpdate = LocalDate.now();
    }

    public void payRecurringExpenseCycles(RecurringExpense recurringExpense) {
        recurringExpense.nextPayment(LocalDate.now());
        Money recurringExpenseTotalAmount = recurringExpense.getAmount().multiply(recurringExpense.getCycles());

        if(!balance.isGreater(recurringExpenseTotalAmount)) {
            throw new InsufficientFundsException("Cant pay expense with current wallet.");
        }

        recurringExpense.updateStatus(BillStatus.PAID);
        balance.subtract(recurringExpenseTotalAmount);
        lastBalanceUpdate = LocalDate.now();
    }

    public void receiveRecurringIncomePaymentCycles(RecurringIncome recurringIncome) {
        Money recurringIncomeTotalAmount = recurringIncome.getAmount().multiply(recurringIncome.getCycles());
        recurringIncome.updateStatus(BillStatus.PAID);
        balance = balance.add(recurringIncomeTotalAmount);
        lastBalanceUpdate = LocalDate.now();
    }
 }

package dev.gabriel.wallet.entities;

import dev.gabriel.bill.entities.IRecurringBill;
import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.shared.exceptions.InsufficientFundsException;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import dev.gabriel.wallet.events.ExpensePaidEvent;
import dev.gabriel.wallet.events.IncomeReceivedEvent;
import dev.gabriel.wallet.events.WalletCreatedEvent;
import dev.gabriel.wallet.events.WalletUpdatedEvent;
import dev.gabriel.wallet.valueobjects.WalletId;
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
        super(WalletId.create(id));
        this.name = name;
        this.comment = comment;
        this.balance = balance;
        this.initialBalance = balance;
        this.userId = userId;
        this.lastBalanceUpdate = getCreatedAt().toLocalDate();
    }

    public static Wallet create(String id, Money balance, String name, String comment, Identity userId) {
        Wallet wallet = new Wallet(id, name, comment, balance, userId);
        wallet.addEvent(new WalletCreatedEvent(wallet));
        return wallet;
    }

    public void rename(String name) {
        this.name = name;
        addEvent(new WalletUpdatedEvent(this));
    }

    public void changeComment(String comment) {
        this.comment = comment;
        addEvent(new WalletUpdatedEvent(this));
    }

    public void changeBalance(Money amount) {
        balance = amount;
        addEvent(new WalletUpdatedEvent(this));
    }

    @Override
    public WalletId getId() {
        return (WalletId) id;
    }

    public void payExpense(Expense expense) {
        Money total;
        if(expense instanceof IRecurringBill recurringBill) {
            recurringBill.nextPayment(LocalDate.now());
            total = expense.getAmount().multiply(recurringBill.getCycles());
        }else total = expense.getAmount();

        if(balance.compareTo(total) < 0) throw new InsufficientFundsException("Wallet don't have the necessary funds to pay it.");
        balance = balance.subtract(total);
        addEvent(new ExpensePaidEvent(this, expense.getId()));
    }

    public void receiveIncome(Income income) {
        Money total;
        if(income instanceof IRecurringBill recurringBill) {
            recurringBill.nextPayment(LocalDate.now());
            total = income.getAmount().multiply(recurringBill.getCycles());
        }else total = income.getAmount();

        balance = balance.add(total);
        addEvent(new IncomeReceivedEvent(this, income.getId()));
    }
 }

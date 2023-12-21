package dev.gabriel.bill.entities.income;

import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.events.income.IncomeCreatedEvent;
import dev.gabriel.bill.events.income.IncomeRemovedEvent;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

public class CommonIncome extends Income {
    private CommonIncome(String id, String name, String comment, Money amount, IncomeCategory category, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
    }

    public static CommonIncome create(String id, String name, String comment, Money amount, IncomeCategory category, BillStatus status, Identity userId) {
        CommonIncome commonIncome = new CommonIncome(id, name, comment, amount, category, status, userId);
        commonIncome.addEvent(new IncomeCreatedEvent(commonIncome));
        return commonIncome;
    }

    public void remove() {
        addEvent(new IncomeRemovedEvent(this));
    }
}

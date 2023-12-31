package dev.gabriel.bill.commands;

import dev.gabriel.bill.models.BillType;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.commands.ICommand;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateBillCommand implements ICommand {
    private String name;
    private String comment;
    private BigDecimal amount;
    private CurrencyCode currencyCode;
    private BillType type;
    private UserId userId;
    private RecurringBillId recurringBillId;
}

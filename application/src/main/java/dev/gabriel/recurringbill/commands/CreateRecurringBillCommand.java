package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateRecurringBillCommand implements ICommand {
    private String name;
    private String comment;
    private BigDecimal amount;
    private String currencyCode;
    private String type;
    private String categoryId;
    private String walletId;
    private String reminderId;
    private Long daysRecurrence;
    private Long totalPeriods;
    private LocalDate startDate;
}

package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CreateRecurringBillCommand extends Command {
    private String name;
    private String comment;
    private BigDecimal amount;
    private String currencyCode;
    private String type;
    private String categoryId;
    private String walletId;
    private String reminderId;
    private Long recurrence;
    private Long maxPeriods;
    private LocalDate startDate;
}

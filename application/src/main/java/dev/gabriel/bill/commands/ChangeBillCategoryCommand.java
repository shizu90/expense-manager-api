package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeBillCategoryCommand extends Command {
    private String billId;
    private String categoryId;
}

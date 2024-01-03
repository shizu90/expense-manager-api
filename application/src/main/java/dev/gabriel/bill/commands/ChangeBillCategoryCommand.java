package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

@Getter
public class ChangeBillCategoryCommand implements ICommand {
    private String billId;
    private String categoryId;
}

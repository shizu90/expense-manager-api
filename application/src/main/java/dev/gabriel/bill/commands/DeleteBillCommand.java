package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

@Getter
public class DeleteBillCommand implements ICommand {
    private String billId;
}

package dev.gabriel.bill.commands;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

@Getter
public class DeleteBillCommand implements ICommand {
    private BillId billId;
}

package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EditRecurringBillCommentCommand extends Command {
    private String recurringBillId;
    private String comment;
}

package dev.gabriel.recurringbill.handlers;

import dev.gabriel.recurringbill.commands.EditRecurringBillCommentCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class EditRecurringBillCommentCommandHandler implements ICommandHandler<RecurringBill, EditRecurringBillCommentCommand> {
    private final IRecurringBillRepository recurringBillRepository;

    @Autowired
    public EditRecurringBillCommentCommandHandler(IRecurringBillRepository recurringBillRepository) {
        this.recurringBillRepository = recurringBillRepository;
    }

    @Override
    public RecurringBill handle(EditRecurringBillCommentCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        recurringBill.editComment(command.getComment());

        return recurringBillRepository.save(recurringBill);
    }
}

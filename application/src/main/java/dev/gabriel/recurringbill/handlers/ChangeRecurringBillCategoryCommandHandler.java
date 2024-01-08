package dev.gabriel.recurringbill.handlers;

import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.commands.ChangeRecurringBillCategoryCommand;
import dev.gabriel.recurringbill.exceptions.RecurringBillNotFoundException;
import dev.gabriel.recurringbill.models.RecurringBill;
import dev.gabriel.recurringbill.repositories.IRecurringBillRepository;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.handlers.ICommandHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class ChangeRecurringBillCategoryCommandHandler implements ICommandHandler<RecurringBill, ChangeRecurringBillCategoryCommand> {
    private final IRecurringBillRepository recurringBillRepository;
    private final ICategoryRepository categoryRepository;

    @Autowired
    public ChangeRecurringBillCategoryCommandHandler(IRecurringBillRepository recurringBillRepository,
                                                     ICategoryRepository categoryRepository
    ) {
        this.recurringBillRepository = recurringBillRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public RecurringBill handle(ChangeRecurringBillCategoryCommand command) {
        RecurringBill recurringBill = recurringBillRepository
                .findById(RecurringBillId.create(command.getRecurringBillId())).orElseThrow(() -> new RecurringBillNotFoundException(command.getRecurringBillId()));
        Category category = categoryRepository
                .findById(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));

        recurringBill.changeCategory(category.getId());

        return recurringBillRepository.save(recurringBill);
    }
}

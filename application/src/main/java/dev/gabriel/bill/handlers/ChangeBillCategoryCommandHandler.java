package dev.gabriel.bill.handlers;

import dev.gabriel.bill.commands.ChangeBillCategoryCommand;
import dev.gabriel.bill.exceptions.BillNotFoundException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.repositories.IBillRepository;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.category.exceptions.CategoryNotFoundException;
import dev.gabriel.category.models.Category;
import dev.gabriel.category.repositories.ICategoryRepository;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.handlers.ICommandHandler;

public class ChangeBillCategoryCommandHandler implements ICommandHandler<Bill, ChangeBillCategoryCommand> {
    private final IBillRepository billRepository;
    private final ICategoryRepository categoryRepository;

    public ChangeBillCategoryCommandHandler(IBillRepository billRepository, ICategoryRepository categoryRepository) {
        this.billRepository = billRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Bill execute(ChangeBillCategoryCommand command) {
        Bill bill = billRepository.findById(BillId.create(command.getBillId())).orElseThrow(() -> new BillNotFoundException(command.getBillId()));
        Category category = categoryRepository.findById(CategoryId.create(command.getCategoryId())).orElseThrow(() -> new CategoryNotFoundException(command.getCategoryId()));
        bill.changeCategory(category.getId());

        return billRepository.save(bill);
    }
}

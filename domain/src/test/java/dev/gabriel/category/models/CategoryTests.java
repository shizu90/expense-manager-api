package dev.gabriel.category.models;

import dev.gabriel.category.events.CategoryCreatedEvent;
import dev.gabriel.category.events.CategoryDeletedEvent;
import dev.gabriel.category.events.CategoryRenamedEvent;
import dev.gabriel.category.exceptions.CategoryValidationException;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CategoryTests {
    Category populate() {
        return Category.create(UUID.randomUUID().toString(), "Food", UserId.create(UUID.randomUUID().toString()));
    }

    @Test
    @DisplayName("Create category test case: success")
    void createCategoryTestCaseSuccess() {
        Category category = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, category);
        Assertions.assertInstanceOf(CategoryCreatedEvent.class, category.getEvents().get(0));
    }

    @Test
    @DisplayName("Rename category test case: success")
    void renameCategoryTestCaseSuccess() {
        Category category = populate();
        category.rename("Entertainment");

        Assertions.assertInstanceOf(CategoryRenamedEvent.class, category.getEvents().get(1));
    }

    @Test
    @DisplayName("Rename category test case: failed")
    void renameCategoryTestCaseFailed() {
        Category category = populate();

        Assertions.assertThrows(CategoryValidationException.class, () -> {
            category.rename(null);
        });
    }

    @Test
    @DisplayName("Delete category test case: success")
    void deleteCategoryTestCaseSuccess() {
        Category category = populate();
        category.delete();

        Assertions.assertInstanceOf(CategoryDeletedEvent.class, category.getEvents().get(1));
    }
}

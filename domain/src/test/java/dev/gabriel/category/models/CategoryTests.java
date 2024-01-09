package dev.gabriel.category.models;

import dev.gabriel.category.events.CategoryCreatedEvent;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CategoryTests {
    Category populate() {
        return Category.create(
                UUID.randomUUID(),
                "Name",
                UserId.create(UUID.randomUUID())
        );
    }

    @Test
    @DisplayName("Create category")
    void createCategory() {
        Category category = populate();

        Assertions.assertInstanceOf(CategoryCreatedEvent.class, category.getEvents().get(0));
        Assertions.assertEquals("Name", category.getName().getValue());
    }

    @Test
    @DisplayName("Create category from event stream")
    void createCategoryFromEventStream() {
        Category category = populate();
        List<DomainEvent> events = Arrays.asList(new DomainEvent[] {
                new CategoryCreatedEvent(
                        category.getId().getValue(),
                        category.getBaseVersion(),
                        category.getName().getValue(),
                        category.getUserId().getValue()
                )
        });
        Category categoryFromEventStream = Category.create(category.getId().getValue(), events);

        Assertions.assertEquals(category.getId(), categoryFromEventStream.getId());
    }
}

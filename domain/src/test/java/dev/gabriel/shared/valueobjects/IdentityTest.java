package dev.gabriel.shared.valueobjects;

import dev.gabriel.shared.entities.ValueObject;
import dev.gabriel.shared.valueobjects.Identity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class IdentityTest {
    @Test
    @DisplayName("Should create identity successfully.")
    public void createIdentityTestCase() {
        Identity identity = Identity.create(UUID.randomUUID().toString());

        Assertions.assertInstanceOf(ValueObject.class, identity);
    }
}

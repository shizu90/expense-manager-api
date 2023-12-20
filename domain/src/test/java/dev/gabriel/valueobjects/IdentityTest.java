package dev.gabriel.valueobjects;

import dev.gabriel.primitives.ValueObject;
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

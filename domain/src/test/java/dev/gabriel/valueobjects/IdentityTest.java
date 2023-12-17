package dev.gabriel.valueobjects;

import dev.gabriel.primitives.ValueObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IdentityTest {
    @Test
    @DisplayName("Should create identity successfully.")
    public void createIdentityTestCase() {
        Identity identity = Identity.create(1L);

        Assertions.assertInstanceOf(ValueObject.class, identity);
    }
}

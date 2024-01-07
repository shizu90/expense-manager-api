package dev.gabriel.wallet.valueobjects;

import dev.gabriel.shared.valueobjects.ValueObject;
import dev.gabriel.wallet.exceptions.WalletValidationException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class WalletName extends ValueObject {
    private final String value;

    private WalletName(String value) {
        this.value = value;
    }

    public static WalletName create(String value) {
        return new WalletName(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 255) {
            throw new WalletValidationException("Name", "Name must have between 1 and 255 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

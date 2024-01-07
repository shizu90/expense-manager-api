package dev.gabriel.wallet.valueobjects;

import dev.gabriel.shared.valueobjects.ValueObject;
import dev.gabriel.wallet.exceptions.WalletValidationException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class WalletDescription extends ValueObject {
    private final String value;

    private WalletDescription(String value) {
        this.value = value;
    }

    public static WalletDescription create(String value) {
        return new WalletDescription(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new WalletValidationException("Description", "Description must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}

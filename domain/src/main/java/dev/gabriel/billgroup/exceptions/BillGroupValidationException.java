package dev.gabriel.billgroup.exceptions;

import dev.gabriel.shared.exceptions.DataValidationException;

public class BillGroupValidationException extends DataValidationException {
    public BillGroupValidationException(String field, String message) {
        super("Bill group validation failed on " + field + ": " + message);
    }
}

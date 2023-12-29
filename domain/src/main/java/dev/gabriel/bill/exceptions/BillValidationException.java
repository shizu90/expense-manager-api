package dev.gabriel.bill.exceptions;

import dev.gabriel.shared.exceptions.DataValidationException;

public class BillValidationException extends DataValidationException {
    public BillValidationException(String field, String message) {
        super("Bill validation failed on " + field + ": " + message);
    }
}

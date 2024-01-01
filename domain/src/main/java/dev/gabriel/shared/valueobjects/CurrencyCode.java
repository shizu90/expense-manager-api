package dev.gabriel.shared.valueobjects;

public enum CurrencyCode {
    USD("usd"),
    EUR("eur"),
    BRL("brl");

    private final String value;

    CurrencyCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

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

    public static CurrencyCode getConstant(String value) {
        return switch(value) {
            case "usd" -> CurrencyCode.USD;
            case "eur" -> CurrencyCode.EUR;
            case "brl" -> CurrencyCode.BRL;
            default -> throw new IllegalArgumentException("Not a valid currency code.");
        };
    }
}

package dev.gabriel.wallet.models;

public enum WalletType {
    DEBIT_CARD("debit_card"),
    CREDIT_CARD("credit_card"),
    INVESTMENTS_ACCOUNT("investments_account"),
    CASH("cash"),
    ONLINE_PAYMENT("online_payment");

    private final String value;

    WalletType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

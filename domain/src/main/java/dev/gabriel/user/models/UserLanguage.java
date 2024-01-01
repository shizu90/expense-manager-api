package dev.gabriel.user.models;

public enum UserLanguage {
    PT_BR("pt_br"),
    EN_US("en_us");

    private final String value;

    UserLanguage(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

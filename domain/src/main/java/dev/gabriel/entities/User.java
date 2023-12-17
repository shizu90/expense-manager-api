package dev.gabriel.entities;

import dev.gabriel.exceptions.MaxReachedException;
import dev.gabriel.primitives.AggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User extends AggregateRoot {
    private List<Object> wallets;
    private UserConfiguration configuration;

    private User(Long id, String name, String email, String passwd) {
        super(id);
        this.wallets = new ArrayList<>();
        this.configuration = UserConfiguration.create(id, name, email, passwd);
    }

    public static User create(Long id, String name, String email, String passwd) {
        return new User(id, name, email, passwd);
    }

    public void addWallet(Wallet wallet) {
        if(wallets.size() == 4) throw new MaxReachedException("Reached max number of wallets.");
        wallets.add(wallet);
    }

    public void removeWallet(Wallet wallet) {
        wallets.remove(wallet);
    }
}

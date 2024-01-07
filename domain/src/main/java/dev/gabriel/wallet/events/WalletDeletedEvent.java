package dev.gabriel.wallet.events;

public class WalletDeletedEvent extends WalletEvent {
    public WalletDeletedEvent(String walletId, Long version) {
        super(walletId, version);
    }
}

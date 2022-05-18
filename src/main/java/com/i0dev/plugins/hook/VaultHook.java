package com.i0dev.plugins.hook;

import com.i0dev.plugins.PotFillPlugin;
import com.i0dev.plugins.template.AbstractHook;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;

public class VaultHook extends AbstractHook {
    Economy economy;
    @Override
    public void initialize() {
        economy = PotFillPlugin.getPlugin().getServer().getServicesManager().getRegistration(Economy.class).getProvider();
    }

    public double getBalance(Player player) {
        return economy.getBalance(player);
    }

    public EconomyResponse withdrawMoney(Player player, double amount) {
        return economy.withdrawPlayer(player, amount);
    }
}

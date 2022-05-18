package com.i0dev.plugin.potfill.command;

import com.i0dev.plugin.potfill.hook.VaultHook;
import com.i0dev.plugin.potfill.object.Pair;
import com.i0dev.plugin.potfill.template.AbstractCommand;
import com.i0dev.plugin.potfill.utility.MsgUtil;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CmdPotFill extends AbstractCommand {

    @Getter
    public static final CmdPotFill instance = new CmdPotFill();

    private ItemStack getPotion() {
        return new ItemStack(Material.valueOf(cnf().getString("potionMaterial")), 1, (short) cnf().getInt("potionData"));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            base(sender, args);
        } else {
            switch (args[0].toLowerCase()) {
                case "reload":
                    reload(sender, args);
                    break;
                case "version":
                case "ver":
                    version(sender, args);
                    break;
                case "help":
                default:
                    help(sender, args);
            }
        }
    }

    private void base(CommandSender sender, String[] args) {
        if (!hasPermission(sender, "use")) {
            MsgUtil.msg(sender, msg().getString("noPermission"));
            return;
        }

        if (!(sender instanceof Player)) {
            MsgUtil.msg(sender, msg().getString("cantRunAsConsole"));
            return;
        }

        Player player = (Player) sender;
        VaultHook eco = plugin.getHook(VaultHook.class);
        int amountToGive = 0;
        int pricePerCommand = cnf().getInt("pricePerCommand");
        int pricePerPotion = cnf().getInt("pricePerPotion");

        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack == null || itemStack.getType() == null) amountToGive++;
        }

        if (amountToGive == 0) {
            MsgUtil.msg(sender, msg().getString("fullInventory"));
            return;
        }

        if (cnf().getBoolean("chargeForEachPotion")) {
            int amountGiven = 0;
            for (int i = 0; i < amountToGive; i++) {
                double balance = eco.getBalance(player);
                if (hasPermission(sender, "bypass") || balance >= pricePerPotion) {
                    player.getInventory().addItem(getPotion());
                    if (!hasPermission(sender, "bypass"))
                        eco.withdrawMoney(player, pricePerPotion);
                    amountGiven++;
                } else
                    break;
            }
            long price = (long) amountGiven * pricePerPotion;
            if (hasPermission(sender, "bypass"))
                price = 0;
            MsgUtil.msg(player, msg().getString("purchased"), new Pair<>("{amt}", String.valueOf(amountGiven)), new Pair<>("{price}", price + ""));

        } else {
            double balance = eco.getBalance(player);
            if (hasPermission(sender, "bypass") || balance >= pricePerCommand) {
                for (int i = 0; i < amountToGive; i++) {
                    player.getInventory().addItem(getPotion());
                }
                if (!hasPermission(sender, "bypass"))
                    eco.withdrawMoney(player, pricePerCommand);
            }
            long price = pricePerCommand;
            if (hasPermission(sender, "bypass"))
                price = 0;
            MsgUtil.msg(player, msg().getString("purchased"), new Pair<>("{amt}", String.valueOf(amountToGive)), new Pair<>("{price}", price + ""));
        }
    }


    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompleteHelper(args[0], Arrays.asList("reload", "help", "version"));
        return blank;
    }
}

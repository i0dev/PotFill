package com.i0dev.plugin.potfill.config;

import com.i0dev.plugin.potfill.template.AbstractConfiguration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageConfig extends AbstractConfiguration {

    public MessageConfig(String path, String... header) {
        super(path, header);
    }

    protected void setValues() {
        config.set("notEnoughMoney", "&cYou don't have enough money to use this.");
        config.set("fullInventory", "&cYour inventory is full.");
        config.set("purchased", "&aYou have successfully purchased &c{amt} potions&a for &c${price}&a.");

        config.set("reloadedConfig", "&7You have&a reloaded&7 the configuration.");
        config.set("noPermission", "&cYou don not have permission to run that command.");
        config.set("cantFindPlayer", "&cThe player: &f{player}&c cannot be found!");
        config.set("invalidNumber", "&cThe number &f{num} &cis invalid! Try again.");
        config.set("cantRunAsConsole", "&cYou cannot run this command from console.");

        config.set("helpPageTitle", "&8_______&r&8[&r &c&lPotFill &8]_______");
        config.set("helpPageFormat", " &c* &7/{cmd}");
    }
}

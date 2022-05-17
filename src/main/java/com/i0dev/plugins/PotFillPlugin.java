package com.i0dev.plugins;

import com.i0dev.plugins.commands.CmdPotFill;
import com.i0dev.plugins.config.GeneralConfig;
import com.i0dev.plugins.config.MessageConfig;
import com.i0dev.plugins.hooks.PlaceholderAPIHook;
import com.i0dev.plugins.hooks.VaultHook;
import com.i0dev.plugins.managers.ConfigManager;
import com.i0dev.plugins.objects.CorePlugin;
import lombok.Getter;

@Getter
public class PotFillPlugin extends CorePlugin {
    @Getter
    private static PotFillPlugin plugin;
    public static final String PERMISSION_PREFIX = "potfill";

    @Override
    public void startup() {
        plugin = this;

        // Managers
        registerManager(ConfigManager.getInstance());

        // Hooks
        registerHook(new VaultHook(), "vault");
        if (isPluginEnabled("PlaceholderAPI"))
            registerHook(new PlaceholderAPIHook(), "papi");

        // Configs
        registerConfig(new GeneralConfig("config.yml", "Main configuration file", "Plugin created by i0dev"));
        registerConfig(new MessageConfig("messages.yml", "Main messaging configuration"));

        // Commands
        registerCommand(CmdPotFill.getInstance(), "potfill");
    }

    @Override
    public void shutdown() {

    }

}

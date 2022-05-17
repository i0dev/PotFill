package com.i0dev.plugins.objects;

import com.i0dev.plugins.PotFillPlugin;
import com.i0dev.plugins.config.GeneralConfig;
import com.i0dev.plugins.config.MessageConfig;
import com.i0dev.plugins.templates.*;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public abstract class CorePlugin extends JavaPlugin {

    Set<AbstractConfiguration> configs = new HashSet<>();
    Set<AbstractManager> managers = new HashSet<>();
    Set<AbstractCommand> commands = new HashSet<>();
    Set<AbstractHook> hooks = new HashSet<>();

    @Override
    public void onEnable() {
        startup();
        System.out.println("\u001B[32m" + getDescription().getName() + " by: " + getDescription().getAuthors().get(0) + " has been enabled.");
    }

    public abstract void startup();

    @Override
    public void onDisable() {
        shutdown();
        HandlerList.unregisterAll(PotFillPlugin.getPlugin());
        Bukkit.getScheduler().cancelTasks(PotFillPlugin.getPlugin());
        commands.forEach(AbstractCommand::deinitialize);
        hooks.forEach(AbstractHook::deinitialize);
        managers.forEach(AbstractManager::deinitialize);
        System.out.println("\u001B[31m" + getDescription().getName() + " by: " + getDescription().getAuthors().get(0) + " has been disabled.");
    }

    public abstract void shutdown();

    public void registerManager(AbstractManager manager) {
        if (manager.isLoaded()) manager.deinitialize();
        manager.initialize();
        manager.setLoaded(true);
    }


    public void registerHook(AbstractHook hook, String hookName) {
        hook.setName(hookName);
        if (hook.isLoaded()) hook.deinitialize();
        hook.initialize();
        hook.setLoaded(true);
        hooks.add(hook);
        System.out.println("\u001B[32mRegistered Hook: " + hookName);
    }

    public void registerCommand(AbstractCommand command, String commandName) {
        command.setCommandName(commandName);
        command.setPlugin(PotFillPlugin.getPlugin());
        if (command.isLoaded()) command.deinitialize();
        command.initialize();
        command.setLoaded(true);

        getCommand(command.getCommandName()).setExecutor(command);
        getCommand(command.getCommandName()).setTabCompleter(command);

        commands.add(command);
    }

    public void registerListener(AbstractListener listener) {
        getServer().getPluginManager().registerEvents(listener, PotFillPlugin.getPlugin());
    }

    public void registerConfig(AbstractConfiguration configuration) {
        configs.add(configuration);
        configuration.getConfig().save();
    }

    public void reloadConfig() {
        configs.forEach(configuration -> configuration.getConfig().reloadConfig());
    }

    public SimpleConfig getConfig(Class<? extends AbstractConfiguration> clazz) {
        AbstractConfiguration c = configs.stream().filter(config -> config.getClass().equals(clazz)).findFirst().orElse(null);
        if (c == null) return null;
        return c.getConfig();
    }

    public <T> T getHook(Class<T> clazz) {
        return (T) hooks.stream().filter(hook -> hook.getClass().equals(clazz)).findFirst().orElse(null);
    }

    public boolean isHookEnabled(String hookName) {
        return hooks.stream().anyMatch(hook -> hook.getName().equalsIgnoreCase(hookName));
    }

    public boolean isPluginEnabled(String name) {
        return getServer().getPluginManager().isPluginEnabled(name);
    }

    public SimpleConfig cnf() {
        return getConfig(GeneralConfig.class);
    }

    public SimpleConfig msg() {
        return getConfig(MessageConfig.class);
    }


}

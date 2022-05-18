package com.i0dev.plugins.hook;

import com.i0dev.plugins.template.AbstractHook;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderAPIHook extends AbstractHook {

    public String replace(Player player, String message) {
        return PlaceholderAPI.setPlaceholders(player, message);
    }

}

package com.i0dev.plugin.potfill.template;


import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Listener;

@Getter
@Setter
public abstract class AbstractManager implements Listener {

    public boolean loaded = false;
    public boolean listener = false;

    public void initialize() {

    }

    public void deinitialize() {

    }
}

package com.i0dev.plugins.template;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractHook {

    public boolean loaded = false;
    public String name = "";

    public void initialize() {

    }

    public void deinitialize() {

    }
}

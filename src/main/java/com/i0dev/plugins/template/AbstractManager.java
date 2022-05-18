package com.i0dev.plugins.template;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractManager {

    public boolean loaded = false;

    public void initialize() {

    }

    public void deinitialize() {

    }
}

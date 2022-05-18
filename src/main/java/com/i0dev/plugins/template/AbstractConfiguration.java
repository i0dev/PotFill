package com.i0dev.plugins.template;

import com.i0dev.plugins.object.SimpleConfig;
import com.i0dev.plugins.manager.ConfigManager;
import lombok.Getter;

@Getter
public abstract class AbstractConfiguration {

    protected transient SimpleConfig config;

    public AbstractConfiguration(String path, String... header) {
        String[] head;
        if (header.length == 0) {
            head = new String[]{
                    "Plugin created by i0dev"
            };
        } else head = header;


        this.config = ConfigManager.getInstance().getNewConfig(path, head);
        setValues();
    }


    protected abstract void setValues();

}

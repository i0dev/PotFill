package com.i0dev.plugin.potfill.config;

import com.i0dev.plugin.potfill.template.AbstractConfiguration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralConfig extends AbstractConfiguration {
    public GeneralConfig(String path, String... header) {
        super(path, header);
    }

    protected void setValues() {
        config.set("chargeForEachPotion", true,
                "This is the toggle between weather to charge per potion, or just charge per command usage.",
                "true -> each potion will cost money, and that can be configured below in the 'pricePerPotion' value.",
                "false -> regardless of how many potions you receive it will only cost one flat rate, which can be configured with the 'priceForRunningCommand' value."
        );

        config.set("pricePerPotion", 20);
        config.set("pricePerCommand", 0);

        config.set("potionMaterial", "POTION",
                "This will be the material type of the potion to give with PotFill");
        config.set("potionData", 16421,
                "The data for the material to give with PotFill.");
    }
}

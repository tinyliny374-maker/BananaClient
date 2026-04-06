package com.example.client.modules.combat;

import com.ovey.client.module.Module;
import com.ovey.client.module.Category;
import com.ovey.client.settings.NumberSetting;

public class Reach extends Module {

    public NumberSetting reach = new NumberSetting("Reach", 3.0, 3.0, 10.0, 0.1);

    public Reach() {
        super("Reach", "Extends attack reach", Category.COMBAT);
        addSettings(reach);
    }

    public double getReach() {
        if (isEnabled()) {
            return reach.getValue();
        }
        return 3.0;
    }
}

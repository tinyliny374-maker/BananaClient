package com.ovey.client.modules.exploit;

import com.ovey.client.module.Module;
import com.ovey.client.module.Category;

public class NewChunks extends Module {

    public NewChunks() {
        super("NewChunks", "Shows newly generated chunks", Category.EXPLOIT);
    }

    @Override
    public void onEnable() {
        // Start tracking chunks
    }

    @Override
    public void onDisable() {
        // Stop tracking
    }
}

package com.example.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class FlyModule {

    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static boolean enabled = false;

    private static final KeyBinding flyKey = new KeyBinding(
            "key.fly.toggle",
            GLFW.GLFW_KEY_F,
            "key.category.client"
    );

    public static void onTick() {
        while (flyKey.wasPressed()) {
            enabled = !enabled;
        }

        if (enabled && mc.player != null) {
            mc.player.getAbilities().allowFlying = true;
            mc.player.getAbilities().flying = true;
        } else if (mc.player != null) {
            mc.player.getAbilities().flying = false;
            mc.player.getAbilities().allowFlying = false;
        }
    }
}

package com.ovey.client.modules.render;

import com.ovey.client.module.Module;
import com.ovey.client.module.Category;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.entity.BarrelBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;

public class StorageESP extends Module {

    private final MinecraftClient mc = MinecraftClient.getInstance();

    public StorageESP() {
        super("StorageESP", "Highlights storage blocks", Category.RENDER);
    }

    @Override
    public void onTick() {
        if (mc.world == null) return;

        for (BlockEntity blockEntity : mc.world.blockEntities) {

            if (blockEntity instanceof ChestBlockEntity ||
                blockEntity instanceof ShulkerBoxBlockEntity ||
                blockEntity instanceof BarrelBlockEntity) {

                BlockPos pos = blockEntity.getPos();

                // This prints location (you can replace with ESP rendering)
                System.out.println("Storage found at: " + pos.toShortString());
            }
        }
    }
}

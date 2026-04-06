package com.ovey.client.modules.exploit;

import com.ovey.client.module.Module;
import com.ovey.client.module.Category;
import com.ovey.client.settings.BooleanSetting;
import com.ovey.client.settings.NumberSetting;
import com.ovey.client.settings.ColorSetting;

import net.minecraft.client.MinecraftClient;
import net.minecraft.block.entity.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ChunkFinder extends Module {

    private final MinecraftClient mc = MinecraftClient.getInstance();

    // Settings (Right click menu)
    public NumberSetting range = new NumberSetting("Range", 100, 10, 500, 10);
    public ColorSetting chunkColor = new ColorSetting("Chunk Color", Color.RED);

    public BooleanSetting chests = new BooleanSetting("Chests", true);
    public BooleanSetting shulkers = new BooleanSetting("Shulkers", true);
    public BooleanSetting barrels = new BooleanSetting("Barrels", true);
    public BooleanSetting hoppers = new BooleanSetting("Hoppers", true);

    private final Set<ChunkPos> foundChunks = new HashSet<>();

    public ChunkFinder() {
        super("ChunkFinder", "Finds chunks with storage", Category.EXPLOIT);

        addSettings(
                range,
                chunkColor,
                chests,
                shulkers,
                barrels,
                hoppers
        );
    }

    @Override
    public void onTick() {

        if (mc.world == null || mc.player == null) return;

        BlockPos playerPos = mc.player.getBlockPos();

        for (BlockEntity blockEntity : mc.world.blockEntities) {

            BlockPos pos = blockEntity.getPos();

            if (pos.isWithinDistance(playerPos, range.getValue())) {

                if (
                        (chests.getValue() && blockEntity instanceof ChestBlockEntity) ||
                        (shulkers.getValue() && blockEntity instanceof ShulkerBoxBlockEntity) ||
                        (barrels.getValue() && blockEntity instanceof BarrelBlockEntity) ||
                        (hoppers.getValue() && blockEntity instanceof HopperBlockEntity)
                ) {

                    ChunkPos chunk = new ChunkPos(pos);

                    if (!foundChunks.contains(chunk)) {
                        foundChunks.add(chunk);

                        System.out.println("Storage Chunk Found: " + chunk.x + ", " + chunk.z);
                    }
                }
            }
        }
    }

    @Override
    public void onRender3D() {

        for (ChunkPos chunk : foundChunks) {

            int x = chunk.x * 16;
            int z = chunk.z * 16;

            // Render Chunk Box
            RenderUtil.drawChunkBox(
                    x,
                    z,
                    chunkColor.getColor()
            );
        }
    }
}

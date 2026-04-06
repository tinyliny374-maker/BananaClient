package com.ovey.client.modules.exploit;

import com.ovey.client.module.Module;
import com.ovey.client.module.Category;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.util.math.ChunkPos;

import java.util.HashSet;
import java.util.Set;

public class NewChunks extends Module {

    private final MinecraftClient mc = MinecraftClient.getInstance();
    private final Set<ChunkPos> seenChunks = new HashSet<>();

    public NewChunks() {
        super("NewChunks", "Detects newly generated chunks", Category.EXPLOIT);
    }

    @Override
    public void onEnable() {
        seenChunks.clear();
    }

    @Override
    public void onTick() {
        if (mc.world == null) return;

        ClientWorld world = mc.world;

        for (WorldChunk chunk : world.getChunkManager().chunks.chunks.values()) {
            ChunkPos pos = chunk.getPos();

            if (!seenChunks.contains(pos)) {
                seenChunks.add(pos);

                // New chunk detected
                System.out.println("New Chunk Found: " + pos.x + ", " + pos.z);
            }
        }
    }
}

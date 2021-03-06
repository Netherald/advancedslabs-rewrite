package org.inventivetalent.advancedslabs.entity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.IBlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.block.data.CraftBlockData;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class EntitySpawner_v1_17_R2 implements IEntitySpawner {
    @Override
    public ISlabFallingBlock spawnFallingBlock(Location location, Material material, String data) {
        // From CraftBlockData

        IBlockData blockData = CraftBlockData.newData(material,data).getState();

        SlabEntityFallingSand_v1_17_R2 entity = new SlabEntityFallingSand_v1_17_R2(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), blockData);
        ((CraftWorld) location.getWorld()).getHandle().addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return entity;
    }

    @Override
    public ISlabFallingBlock spawnFallingBlock(Location location, Material material, byte data) {
        ///TODO: find a way to still support IDs/data properly
        SlabEntityFallingSand_v1_17_R2 entity = new SlabEntityFallingSand_v1_17_R2(((CraftWorld) location.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ(), Block.getByCombinedId(material.getId()));
        ((CraftWorld) location.getWorld()).getHandle().addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return entity;
    }
}

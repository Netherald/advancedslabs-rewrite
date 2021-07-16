package org.inventivetalent.advancedslabs.entity;

import net.minecraft.core.BlockPosition;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.item.EntityFallingBlock;
import net.minecraft.world.level.World;
import net.minecraft.world.level.block.state.IBlockData;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;

import java.lang.reflect.Field;
import java.util.UUID;

public class SlabEntityFallingSand_v1_17_R1 extends EntityFallingBlock implements ISlabFallingBlock {

    private boolean canDie = false;

    public SlabEntityFallingSand_v1_17_R1(World world) {
        super(EntityTypes.C, world);
    }

    public SlabEntityFallingSand_v1_17_R1(World world, double d0, double d1, double d2, IBlockData iblockdata) {
        super(world, d0, d1, d2, iblockdata);
    }

    @Override
    public UUID getUniqueId() {
        return this.aj;
    }

    @Override
    public void setTicksLived(int ticksLived) {
        this.b = ticksLived;
    }

    @Override
    public void setDropItem(boolean dropItem) {
        this.c = dropItem;
    }

    @Override
    public void setCustomName(String s) {
        super.setCustomName(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + s + "\"}"));
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        super.setCustomNameVisible(flag);
    }

    @Override
    public boolean isDead() {
        return !super.isAlive();
    }

    @Override
    public void teleport(Location location) {
        if (location != null) {
            org.bukkit.World world = location.getWorld();
            if (world != null) {
                super.teleportTo(((CraftWorld) world).getHandle().getLevel(), new BlockPosition(location.getX(), location.getY(), location.getZ()));
            }
        }
    }

    @Override
    public void remove() {
        if (!canDie) {
            //dead = false;
            try {
                Field field = Entity.class.getDeclaredField("aB");
                field.setAccessible(true);
                field.set(this, null);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            return;
        }
        die();
    }

    @Override
    public void eject() {
    }

    @Override
    public void setRiding(Object vehicle) {
        super.a((Entity) vehicle, true);
    }

    @Override
    public void stopRiding() {
        super.stopRiding();
    }

    @Override
    public void allowDeath() {
        this.canDie = true;
    }

    @Override
    public void denyDeath() {
        this.canDie = false;
    }

    /*
    @Override
    public void die() {
        if (!canDie) {
            //dead = false;
            try {
                Field field = Entity.class.getDeclaredField("aB");
                field.setAccessible(true);
                field.set(this, null);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            return;
        }
        super.die();
    }
     */

    @Override
    public void tick() {
        //Ignore any updates.
    }

}

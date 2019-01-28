package endreborn.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

public class TeleporterOver implements ITeleporter {

    @Override
    public void placeEntity(World world, Entity entity, float yaw) {
        BlockPos pos = entity.getPosition();
        entity.setPositionAndUpdate(pos.getX(), pos.getY() + 0.1D, pos.getZ());
        entity.motionX = 0f;
        entity.motionY = 0f;
        entity.motionZ = 0f;

    }

}
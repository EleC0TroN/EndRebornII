package endreborn.mod.entity;

import javax.annotation.Nullable;

import endreborn.handlers.LootTableHandler;
import endreborn.handlers.SoundHandler;
import endreborn.utils.EndHelper;
// import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.BossInfo;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntityLord extends EntityLordBase {
    private final BossInfoServer bossInfo = (BossInfoServer) (new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE,
            BossInfo.Overlay.PROGRESS)).setDarkenSky(true);

    @Override
    public boolean isImmuneToExplosions() {
        return true;
    }

    public EntityLord(World worldIn) {
        super(worldIn);
        this.isImmuneToFire = true;
        this.experienceValue = 10;
    }

    public static void registerFixesWatcher(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityLord.class);
    }

    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere() && this.world.canSeeSky(new BlockPos(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.34D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
    }

    protected boolean shouldBurnInDay() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ENDERMEN_AMBIENT;
    }

    @Override
    public void addTrackingPlayer(EntityPlayerMP player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    public boolean isNonBoss() {
        return false;
    }

    @Override
    public void setCustomNameTag(String name) {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }

    @Override
    public void removeTrackingPlayer(EntityPlayerMP player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 8.0F));
    }
    @Override
    protected void onDeathUpdate() {
        this.experienceValue = 100;
        world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 2.0F, 2.0F, 2.0F, null);
        this.setDead();
        if (!world.isRemote) {
            EndHelper.LordGroup(world, "tile.ender_lord.lord");
            EntityEnderman ender = new EntityEnderman(world);
            ender.setLocationAndAngles(this.posX, this.posY + 1, this.posZ, this.rotationYaw, this.rotationPitch);
            this.world.spawnEntity(ender);
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableHandler.LORD;
    }

    protected boolean teleportRandomly() {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.posY + (double) (this.rand.nextInt(64) - 32);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
        return this.teleportTo(d0, d1, d2);
    }

    protected boolean teleportToEntity(Entity p_70816_1_) {
        Vec3d vec3d = new Vec3d(this.posX - p_70816_1_.posX, this.getEntityBoundingBox().minY + (double) (this.height / 2.0F) - p_70816_1_.posY + (double) p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
        vec3d = vec3d.normalize();
        double d0 = 16.0D;
        this.world.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, SoundEvents.ENTITY_ENDERMEN_TELEPORT, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
        double d1 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
        double d2 = this.posY + (double) (this.rand.nextInt(16) - 8) - vec3d.y * 16.0D;
        double d3 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
        return this.teleportTo(d1, d2, d3);
    }

    @Override
    protected void updateAITasks() {
        float f = (rand.nextFloat() - 0.5F) * 8.0F;
        float f1 = (rand.nextFloat() - 0.5F) * 4.0F;
        float f2 = (rand.nextFloat() - 0.5F) * 8.0F;

        super.updateAITasks();
        if (this.ticksExisted % 20 == 0) {
            if (this.getHealth() < 40) {
                this.addPotionEffect(new PotionEffect(MobEffects.HASTE, 100, 1));
                this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 1));
                this.heal(2.0F);
            }
        }
        if (this.ticksExisted % 400 == 0) {
            if (this.getHealth() < 150 && this.getHealth() > 120) {
                //TODO: Fix sound not playing on server.
                if (!world.isRemote)
                    this.playSound(SoundHandler.RAGE_1, 1.0F, 1.0F);
                EndHelper.LordGroup(world,"tile.ender_lord.rage_1");
            }
        }
        if (this.ticksExisted % 400 == 0) {
            if (this.getHealth() < 120 && this.getHealth() > 90) {
                //TODO: Fix sound not playing on server.
                if (!world.isRemote)
                    this.playSound(SoundHandler.RAGE_2, 1.0F, 1.0F);
                EndHelper.LordGroup(world,"tile.ender_lord.rage_2");
            }
        }
        if (this.ticksExisted % 400 == 0) {
            if (this.getHealth() < 90 && this.getHealth() > 50) {
                //TODO: Fix sound not playing on server.
                if (!world.isRemote)
                    this.playSound(SoundHandler.RAGE_3, 1.0F, 1.0F);
                EndHelper.LordGroup(world,"tile.ender_lord.rage_3");
            }
        }
        if (this.ticksExisted % 400 == 0) {
            if (this.getHealth() < 30 && this.getHealth() > 10) {
                //TODO: Fix sound not playing on server.
                if (!world.isRemote)
                    this.playSound(SoundHandler.RAGE_4, 1.0F, 1.0F);
                EndHelper.LordGroup(world, "tile.ender_lord.rage_4");
            }
        }
        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(64.0D, 64.0D, 64.0D))) {
            if (entity instanceof EntityWither) {
                if (!world.isRemote) {
                    world.removeEntity(entity);
                    world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, entity.posX + f, entity.posY + 2.0D + f1, entity.posZ + f2, 0.0D, 0.0D, 0.0D);
                }

                EndHelper.LordGroup(world, "tile.ender_lord.wither");
            }
        }

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(64.0D, 64.0D, 64.0D))) {
            if (entity instanceof EntityLord) {
                if (!world.isRemote) {
                    world.removeEntity(entity);

                    if (!entity.isDead)
                        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, entity.posX + f, entity.posY + 2.0D + f1, entity.posZ + f2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }
    private boolean teleportTo(double x, double y, double z)
    {
        net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, x, y, z, 0);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
        boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());

        if (flag)
        {
            this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
        }

        return flag;
    }
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (source instanceof EntityDamageSourceIndirect)
        {
            for (int i = 0; i < 64; ++i)
            {
                if (this.teleportRandomly())
                {
                    return true;
                }
            }

            return false;
        }
        else
        {
            boolean flag = super.attackEntityFrom(source, amount);

            if (source.isUnblockable() && this.rand.nextInt(10) != 0)
            {
                this.teleportRandomly();
            }

            return flag;
        }
    }


    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = super.attackEntityAsMob(entityIn);
        EntityEndermite entityshulkerbullet = new EntityEndermite(world);
        if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof EntityLivingBase)
        {
        	EntityLord.this.world.spawnEntity(entityshulkerbullet);
            float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 140 * (int)f));
        }

        return flag;
    }
}

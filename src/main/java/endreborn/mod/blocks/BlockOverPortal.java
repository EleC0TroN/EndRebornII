package endreborn.mod.blocks;

import endreborn.EndReborn;
import endreborn.init.BlockInit;
import endreborn.init.ItemInit;
import endreborn.utils.IHasModel;
import endreborn.utils.IModelProvider;
import endreborn.world.TeleporterOver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;
public class BlockOverPortal extends BlockBreakable implements IHasModel, IModelProvider {

    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.25D, 0.0D, 1.0D, 0.75D, 1.0D);

    public BlockOverPortal(String name, Material material) {
        super(material, false);
        setUnlocalizedName(name);
        setRegistryName(name);
        setSoundType(SoundType.STONE);
        setLightLevel(1.0F);
        setBlockUnbreakable();

        BlockInit.BLOCKS.add(this);
        ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }


    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn, EntityPlayer player)
    {
        if (!worldIn.isRemote && !entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.isNonBoss() && entityIn.getEntityBoundingBox().intersects(state.getBoundingBox(worldIn, pos).offset(pos)))
        {
            if (entityIn.timeUntilPortal > 0) {
                return;
            }
            worldIn.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.PLAYERS, 0.5F, player.world.rand.nextFloat() * 0.1F + 0.9F);
            Random r = new Random();
            TeleporterOver teleporter = new TeleporterOver();
            if(worldIn.provider.getDimensionType() == DimensionType.THE_END) {
                entityIn.changeDimension(0, teleporter);
                worldIn = entityIn.world;
                int x = (int) (entityIn.posX + (4 - (1 + r.nextInt(8))));
                int z = (int) (entityIn.posZ + (4 - (1 + r.nextInt(8))));
                int y = worldIn.getHeight(x, z);
                entityIn.setPositionAndUpdate(x, y, z);;
                entityIn.timeUntilPortal = 60;
            }
            else if(worldIn.provider.getDimensionType() == DimensionType.OVERWORLD) {
                entityIn.changeDimension(1, teleporter);
                worldIn = entityIn.world;
                int x = (int) (entityIn.posX + (4 - (1 + r.nextInt(8))));
                int z = (int) (entityIn.posZ + (4 - (1 + r.nextInt(8))));
                int y = worldIn.getHeight(x, z);
                entityIn.setPositionAndUpdate(x, y, z);
                entityIn.timeUntilPortal = 60;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        int random = rand.nextInt(100);
        if (random == 0) {
            worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double xPos = (double) ((float) pos.getX() + rand.nextFloat());
            double yPos = pos.getY()+1D;
            double zPos = (double) ((float) pos.getZ() + rand.nextFloat());
            double xSpeed = ((double) rand.nextFloat() - 0.5D) * 0.5D;
            double ySpeed = rand.nextFloat();
            double zSpeed = ((double) rand.nextFloat() - 0.5D) * 0.5D;

            worldIn.spawnParticle(EnumParticleTypes.PORTAL, xPos, yPos, zPos, xSpeed, ySpeed, zSpeed);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (fromPos.getY() == pos.getY() + 1 || fromPos.getY() == pos.getY() - 1) return;
        if (worldIn.getBlockState(fromPos) == Blocks.AIR.getDefaultState()) {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.ORANGE_STAINED_HARDENED_CLAY;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean isActualState)
    {
    }
    @Override
    public void registerModels()
    {
        EndReborn.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

}
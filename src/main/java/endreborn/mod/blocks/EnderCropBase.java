package endreborn.mod.blocks;

import endreborn.EndReborn;
import endreborn.init.BlockInit;
import endreborn.init.ItemInit;
import endreborn.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnderCropBase extends BlockCrops implements IHasModel
{

	public EnderCropBase(String name) 
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(EndReborn.endertab);

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	protected Item getSeed() 
	{
		return null;
	}
	
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

	@Override
	protected Item getCrop() 
	{
		return Items.ENDER_PEARL;
	}
	
	@Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock().isReplaceable(world, pos) && world.getBlockState(pos.down()).getBlock() == Blocks.END_STONE;
    }
	
	@Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        return world.getBlockState(pos.down()).getBlock() == Blocks.END_STONE;
    }
	
	@Override
    public Block.EnumOffsetType getOffsetType() 
	{
    return Block.EnumOffsetType.XYZ;
	}

	@Override
	public void registerModels() 
	{
		EndReborn.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

}
package endreborn.mod.blocks;

import java.util.Random;

import endreborn.init.ItemInit;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockCrystal extends BlockBase {
	
	public BlockCrystal(String name, Material material) {
		super(name, material);
		
		setSoundType(SoundType.STONE);
		setHardness(3.0F);
		setResistance(25.0F);
		setLightLevel(1.0F);
		setHarvestLevel("pickaxe", 2);
		
	} 
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemInit.LORMYTE_CRYSTAL;
	}
}


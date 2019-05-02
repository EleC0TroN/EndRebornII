package endreborn.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesInit 
{

	public static void init() 
	{
		GameRegistry.addSmelting(BlockInit.ORE_WOLFRAMIUM, new ItemStack(ItemInit.INGOT_WOLFRAMIUM, 1), 1.5f);
		GameRegistry.addSmelting(Blocks.END_STONE, new ItemStack(BlockInit.END_GLOW, 1), 0.5f);
		GameRegistry.addSmelting(Blocks.END_BRICKS, new ItemStack(BlockInit.BRICKS_GLOW, 1), 0.5f);
		GameRegistry.addSmelting(BlockInit.END_STONE_SMOOTH, new ItemStack(BlockInit.SMOOTH_GLOW, 1), 0.5f);
		GameRegistry.addSmelting(BlockInit.END_STONE_PILLAR, new ItemStack(BlockInit.PILLAR_GLOW, 1), 0.5f);
		GameRegistry.addSmelting(BlockInit.STAIRS_SMOOTH_END_STONE, new ItemStack(BlockInit.STAIRS_SMOOTH_GLOW_STONE, 1), 0.5f);
		GameRegistry.addSmelting(BlockInit.STAIRS_END_BRICKS, new ItemStack(BlockInit.STAIRS_GLOW_BRICKS, 1), 0.5f);
		GameRegistry.addSmelting(BlockInit.WALL_END_BRICKS, new ItemStack(BlockInit.WALL_GLOW_BRICKS, 1), 0.5f);
		GameRegistry.addSmelting(BlockInit.WALL_SMOOTH_END_STONE, new ItemStack(BlockInit.WALL_SMOOTH_GLOW_STONE, 1), 0.5f);
	}
}
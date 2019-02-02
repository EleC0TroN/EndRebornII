package endreborn.world;

import java.util.ArrayList;
import java.util.Random;

import endreborn.handlers.ConfigsHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Arrays;
import java.util.List;

public class WorldGenCustomStructures implements IWorldGenerator

{

	private static final IBlockState GROUND = Blocks.END_STONE.getDefaultState();
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator generator,
			IChunkProvider provider)
	{
		switch(world.provider.getDimension())
		{
			case 1:
				if(ConfigsHandler.GENERAL.spawnLormyte)
				{
				generateStructure2(new WorldGenStructure("lormyte_crystal"), world, rand, chunkX, chunkZ, 50, Blocks.END_STONE, BiomeEnd.class);
				}
				generateStructure4(new WorldGenStructure("over_portal"), world, rand, chunkX, chunkZ, ConfigsHandler.BALANCE.portalRare, Blocks.AIR, BiomeEnd.class);
				break;

			case 0:
				if(ConfigsHandler.GENERAL.spawnEndIsland)
				{
				generateStructure(new WorldGenStructure("end_island"), world, rand, chunkX, chunkZ, ConfigsHandler.BALANCE.islandRare, Blocks.AIR, BiomePlains.class);
				generateStructure(new WorldGenStructure("end_island"), world, rand, chunkX, chunkZ, ConfigsHandler.BALANCE.islandRare, Blocks.AIR, BiomeForest.class);
				generateStructure(new WorldGenStructure("end_island"), world, rand, chunkX, chunkZ, ConfigsHandler.BALANCE.islandRare, Blocks.AIR, BiomeDesert.class);
				generateStructure(new WorldGenStructure("end_island"), world, rand, chunkX, chunkZ, ConfigsHandler.BALANCE.islandRare, Blocks.AIR, BiomeOcean.class);
				generateStructure(new WorldGenStructure("end_island"), world, rand, chunkX, chunkZ, ConfigsHandler.BALANCE.islandRare, Blocks.AIR, BiomeSavanna.class);
				}
				if(ConfigsHandler.GENERAL.spawnObservatory)
				{
				generateStructure3(new WorldGenStructure("observ"), world, rand, chunkX, chunkZ, 500, Blocks.STONE, BiomeSwamp.class);
				generateStructure3(new WorldGenStructure("observ"), world, rand, chunkX, chunkZ, 500, Blocks.STONE, BiomeForest.class);
				generateStructure3(new WorldGenStructure("observ"), world, rand, chunkX, chunkZ, 600, Blocks.STONE, BiomeDesert.class);
				generateStructure3(new WorldGenStructure("observ"), world, rand, chunkX, chunkZ, 600, Blocks.STONE, BiomeOcean.class);
				}
				generateStructure5(new WorldGenStructure("over_portal_2"), world, rand, chunkX, chunkZ, ConfigsHandler.BALANCE.portalRare, Blocks.GRASS, BiomePlains.class);
				break;

			case -1:

				break;

			case -64:

			
		}
	}

	private void generateStructure2(WorldGenerator generator, World world, Random rand,

			int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)

	{
		List<Class<?>> classesList = Arrays.asList(classes);

		int x = (chunkX * 16) + rand.nextInt(15);
		int z = (chunkZ * 16) + rand.nextInt(15);
		int y = 57;

		BlockPos pos = new BlockPos(x, y, z);

		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(rand.nextInt(chance) == 0)
				{
					generator.generate(world, rand, pos);
				}
			}
		}
	}
	private void generateStructure4(WorldGenerator generator, World world, Random rand,

									int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)

	{
		List<Class<?>> classesList = Arrays.asList(classes);

		int x = (chunkX * 16) + rand.nextInt(15);
		int z = (chunkZ * 16) + rand.nextInt(15);
		int y = 65;

		BlockPos pos = new BlockPos(x, y, z);

		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(rand.nextInt(chance) == 0)
				{
					generator.generate(world, rand, pos);
				}
			}
		}
	}
	private void generateStructure(WorldGenerator generator, World world, Random rand,

								   int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)

	{
		List<Class<?>> classesList = Arrays.asList(classes);

		int x = (chunkX * 16) + rand.nextInt(15);
		int z = (chunkZ * 16) + rand.nextInt(15);
		int y = 100;

		BlockPos pos = new BlockPos(x, y, z);

		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(rand.nextInt(chance) == 0)
				{
					generator.generate(world, rand, pos);
				}
			}
		}
	}

	private void generateStructure3(WorldGenerator generator, World world, Random rand,

									int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)

	{
		List<Class<?>> classesList = Arrays.asList(classes);

		int x = (chunkX * 16) + rand.nextInt(15);
		int z = (chunkZ * 16) + rand.nextInt(15);
		int y = 3;

		BlockPos pos = new BlockPos(x, y, z);

		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(rand.nextInt(chance) == 0)
				{
					generator.generate(world, rand, pos);
				}
			}
		}
	}
	private void generateStructure5(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance, Block topBlock, Class<?>... classes)
	{
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(classes));

		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock);
		BlockPos pos = new BlockPos(x,y,z);

		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();

		if(world.getWorldType() != WorldType.FLAT)
		{
			if(classesList.contains(biome))
			{
				if(random.nextInt(chance) == 0)
				{
					generator.generate(world, random, pos);
				}
			}
		}
	}
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock)
	{
		int y = world.getHeight();
		boolean foundGround = false;

		while(!foundGround && y-- >= 0)
		{
			Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
			foundGround = block == topBlock;
		}

		return y;
	}
}


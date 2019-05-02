package endreborn.handlers;

import endreborn.Reference;
import endreborn.init.BlockInit;
import endreborn.init.ItemInit;
import endreborn.mod.entity.EntityAngryEnder;
import endreborn.mod.entity.EntityWatcher;
import endreborn.utils.EndForge;
import endreborn.world.TeleporterEnd;
import endreborn.world.TeleporterOver;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.*;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.core.pattern.AbstractStyleNameConverter;
import scala.tools.nsc.interpreter.JavapClass;

import java.util.Calendar;
import java.util.Random;


@EventBusSubscriber
public class EventHandler 
{
	@SubscribeEvent
	public void onDragonTick(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		if (living.world.isRemote || !(living instanceof EntityDragon))
			return;
		EntityDragon dragon = (EntityDragon) living;
		if (dragon.deathTicks < 150 || dragon.deathTicks % 10 != 0)
			return;

		for (int i = 0; i < 6; i++) {
			int x = dragon.world.rand.nextInt(256) - 128;
			int z = dragon.world.rand.nextInt(256) - 128;
			BlockPos pos = new BlockPos(x, dragon.world.getHeight(x, z)-1, z);
			if (!dragon.world.isBlockLoaded(pos))
				continue;
			if (dragon.world.getBlockState(pos.down()).getBlock() != Blocks.END_STONE)
				continue;
			dragon.world.setBlockState(pos, BlockInit.DRAGON_ESSENCE.getDefaultState());
		}
	}
	@SubscribeEvent
	public void onDragonTickAsh(LivingEvent.LivingUpdateEvent event) {
		EntityLivingBase living = event.getEntityLiving();
		if (living.world.isRemote || !(living instanceof EntityDragon))
			return;
		EntityDragon dragon = (EntityDragon) living;
		if (dragon.deathTicks < 150 || dragon.deathTicks % 10 != 0)
			return;

		for (int i = 0; i < 6; i++) {
			int x = dragon.world.rand.nextInt(256);
			int z = dragon.world.rand.nextInt(256);
			BlockPos pos = new BlockPos(x, dragon.world.getHeight(x, z), z);
			if (!dragon.world.isBlockLoaded(pos))
				continue;
			if (dragon.world.getBlockState(pos.down()).getBlock() != Blocks.END_STONE)
				continue;
			dragon.world.setBlockState(pos, BlockInit.BLOCK_ASH.getDefaultState());
		}
	}
	@SubscribeEvent
    public static void playerInteractEvent(PlayerInteractEvent.RightClickBlock event)
    {
        ItemStack stack = event.getItemStack();

        EntityPlayer player = event.getEntityPlayer();
        if (event.getHand() == EnumHand.OFF_HAND)
        {
            ItemStack mainStack = player.getHeldItem(EnumHand.MAIN_HAND);
            if (EndForge.hasAction(event.getWorld(), event.getPos(), mainStack, event.getFace()))
            {
                event.setCancellationResult(EnumActionResult.SUCCESS);
                event.setCanceled(true);
                return;
            }
        }

        if (EndForge.hasAction(event.getWorld(), event.getPos(), stack, event.getFace()))
        {
            if (EndForge.performAction(event.getWorld(), event.getPos(), player, stack, event.getFace(), event.getHand()))
            {
                event.setCancellationResult(EnumActionResult.SUCCESS);
                event.setCanceled(true);
            }
        }
    }
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public void onEventDrop(LivingDropsEvent event)
	{
	    if (event.getEntity() instanceof EntityDragon)
	    {
	        event.getDrops().clear();
	        ItemStack itemStackToDrop = new ItemStack(ItemInit.DRAGON_SCALES, 4);
	        event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, 
	              event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
	       
	    }
	    if (event.getEntity() instanceof EntityEnderman)
	    {
	        event.getDrops().clear();
	        ItemStack itemStackToDrop = new ItemStack(ItemInit.ENDER_FLESH, 1);
	        event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, 
	              event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
	    }
	    
	    if (event.getEntity() instanceof EntityWatcher)
	    {
	        event.getDrops().clear();
	        ItemStack itemStackToDrop = new ItemStack(ItemInit.ENDER_FLESH, 1);
	        event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, 
	              event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
	    }
	    if (event.getEntity() instanceof EntityAngryEnder)
	    {
	        event.getDrops().clear();
	        ItemStack itemStackToDrop = new ItemStack(ItemInit.ENDER_FLESH, 1);
	        event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, 
	              event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
	    }
		if (event.getEntity() instanceof EntityEnderman)
		{
			event.getDrops().clear();
			ItemStack itemStackToDrop = new ItemStack(Items.ENDER_PEARL, 1);
			event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX,
					event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
		}

		if (event.getEntity() instanceof EntityWatcher)
		{
			event.getDrops().clear();
			ItemStack itemStackToDrop = new ItemStack(Items.ENDER_EYE, 1);
			event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX,
					event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
		}
		if (event.getEntity() instanceof EntityAngryEnder)
		{
			event.getDrops().clear();
			ItemStack itemStackToDrop = new ItemStack(Items.ENDER_PEARL, 1);
			event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX,
					event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
		}
	} 
	@SubscribeEvent
	public static void onLivingSpawn(LivingSpawnEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if(entity instanceof EntityEnderman) {
			if(entity.world.provider.getDimension() == 1 && entity.world.getDifficulty() != EnumDifficulty.PEACEFUL && !entity.world.isRemote) {
				if(entity.getRNG().nextInt(ConfigsHandler.BALANCE.watcherRare) == 1) {
					EntityWatcher squid = new EntityWatcher(entity.world);
					squid.copyLocationAndAnglesFrom(entity);
					entity.world.spawnEntity(squid);
					entity.setDead();
				} 
			}
		}
		if(entity instanceof EntityEnderman) {
			if(entity.world.provider.getDimension() == 1 && entity.world.getDifficulty() != EnumDifficulty.PEACEFUL && !entity.world.isRemote) {
				if(entity.getRNG().nextInt(ConfigsHandler.BALANCE.angryendRare) == 1) {
					EntityAngryEnder squid1 = new EntityAngryEnder(entity.world);
					squid1.copyLocationAndAnglesFrom(entity);
					entity.world.spawnEntity(squid1);
					entity.setDead();
				} 
			}
		}
	}
	@SubscribeEvent
    public static void onPlayerPosition(LivingHurtEvent event) 
	{
        if(event.getEntityLiving() instanceof EntityPlayerMP && event.getEntityLiving().dimension == 1 
        		&& ConfigsHandler.GENERAL.teleporterEnd && event.getEntityLiving().getPosition().getY() <= -6)
        {
        	EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
        	PlayerList playerList = player.getEntityWorld().getMinecraftServer().getPlayerList();
        	
        	event.setCanceled(true);
        	playerList.transferPlayerToDimension(player, 0, 
        			new TeleporterEnd((WorldServer) player.getEntityWorld(), 
        					player.getPosition().getX(), 250, player.getPosition().getZ()));
        }
        }
	@SubscribeEvent
	public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getModID().equals(Reference.MODID))
		{
			ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);

		}
	}
	@SubscribeEvent
	public static void portalTeleport(TickEvent.PlayerTickEvent event) {
		EntityPlayer entity = event.player;
		World world = entity.world;

		BlockPos pos = new BlockPos(entity.posX, entity.posY - 1, entity.posZ);

		if (entity.world.getBlockState(pos).getBlock() == BlockInit.OVER_PORTAL) {
			if (!world.isRemote && !entity.isRiding() && !entity.isBeingRidden() && entity.isNonBoss()) {
				if (entity.timeUntilPortal > 0)
				{
					return;
				}
				Random r = new Random();
				TeleporterOver teleporter = new TeleporterOver();
				if (world.provider.getDimensionType() == DimensionType.THE_END) {
					entity.changeDimension(0, teleporter);
					world = entity.world;
					int x = (int) (entity.posX + (4 - (1 + r.nextInt(8))));
					int z = (int) (entity.posZ + (4 - (1 + r.nextInt(8))));
					int y = world.getHeight(x, z);
					entity.setPositionAndUpdate(x,y+80, z);

					entity.timeUntilPortal = 60;
				}
			}

		}
	}
	public static boolean isDayI() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) == calendar.MAY && calendar.get(Calendar.DAY_OF_MONTH) == 10;
	}
	public static boolean isDayS() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) == calendar.AUGUST && calendar.get(Calendar.DAY_OF_MONTH) == 20;
	}
	public static boolean isDayB() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) == calendar.SEPTEMBER && calendar.get(Calendar.DAY_OF_MONTH) == 7;
	}
	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent e)
	{
		if (e.getEntity() instanceof EntityPlayer && isDayI())
		{
			EntityPlayer player = (EntityPlayer) e.getEntity();
			player.sendMessage(new TextComponentString("[End: Reborn] Hey, thanks for playing with my mod. Happy birthday to me."));
		}
	}
	@SubscribeEvent
	public void onJoin2(EntityJoinWorldEvent e)
	{
		if (e.getEntity() instanceof EntityPlayer && isDayS())
		{
			EntityPlayer player = (EntityPlayer) e.getEntity();
			player.sendMessage(new TextComponentString("[End: Reborn] Just a very good day;)"));
		}
	}
	@SubscribeEvent
	public void onJoin3(EntityJoinWorldEvent e)
	{
		if (e.getEntity() instanceof EntityPlayer && isDayB())
		{
			EntityPlayer player = (EntityPlayer) e.getEntity();
			player.sendMessage(new TextComponentString("[End: Reborn] Happy birthday to Lord."));
		}
	}
}	

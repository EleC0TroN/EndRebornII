package endreborn.handlers;

import endreborn.init.ItemInit;
import endreborn.mod.entity.EntityAngryEnder;
import endreborn.mod.entity.EntityWatcher;
import endreborn.utils.EndForge;
import endreborn.world.TeleporterEnd;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@EventBusSubscriber
public class EventHandler 
{
	
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
	        ItemStack itemStackToDrop = new ItemStack(ItemInit.ENDER_FLESH, 2);
	        event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, 
	              event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
	    }
	    
	    if (event.getEntity() instanceof EntityWatcher)
	    {
	        event.getDrops().clear();
	        ItemStack itemStackToDrop = new ItemStack(ItemInit.ENDER_FLESH, 2);
	        event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, 
	              event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
	    }
	    if (event.getEntity() instanceof EntityAngryEnder)
	    {
	        event.getDrops().clear();
	        ItemStack itemStackToDrop = new ItemStack(ItemInit.ENDER_FLESH, 2);
	        event.getDrops().add(new EntityItem(event.getEntity().getEntityWorld(), event.getEntity().posX, 
	              event.getEntity().posY, event.getEntity().posZ, itemStackToDrop));
	    }
	} 
	@SubscribeEvent
	public static void onLivingSpawn(LivingSpawnEvent event) {
		EntityLivingBase entity = event.getEntityLiving();
		if(entity instanceof EntityEnderman) {
			if(entity.world.provider.getDimension() == 1 && entity.world.getDifficulty() != EnumDifficulty.PEACEFUL && !entity.world.isRemote) {
				if(entity.getRNG().nextInt(40) == 1) {
					EntityWatcher squid = new EntityWatcher(entity.world);
					squid.copyLocationAndAnglesFrom(entity);
					entity.world.spawnEntity(squid);
					entity.setDead();
				} 
			}
		}
		if(entity instanceof EntityEnderman) {
			if(entity.world.provider.getDimension() == 1 && entity.world.getDifficulty() != EnumDifficulty.PEACEFUL && !entity.world.isRemote) {
				if(entity.getRNG().nextInt(30) == 1) {
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
        		&& ConfigHandler.teleporterEnd && event.getEntityLiving().getPosition().getY() <= -6) 
        {
        	EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
        	PlayerList playerList = player.getEntityWorld().getMinecraftServer().getPlayerList();
        	
        	event.setCanceled(true);
        	playerList.transferPlayerToDimension(player, 0, 
        			new TeleporterEnd((WorldServer) player.getEntityWorld(), 
        					player.getPosition().getX(), 250, player.getPosition().getZ()));
        }
        }
	
}	

package endreborn.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class EndHelper 
{
	public static final String[] GUIMAINMENU_TITLEPANORAMAPATHS = new String[] { "TITLE_PANORAMA_PATHS", "field_73978_o", "o" };
	public static void setTitlePanoramaPaths(ResourceLocation[] titlePanoramaPaths) {
		Field field = ReflectionHelper.findField(GuiMainMenu.class, EndHelper.GUIMAINMENU_TITLEPANORAMAPATHS);
		
		Field modifiersField;
		try {
			modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
	        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

	        field.setAccessible(true);
	        field.set(null, titlePanoramaPaths);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	 public static void dropItemInWorldExact(World world, double x, double y, double z, ItemStack stack)
	    {
	        world.spawnEntity(new EntityItem(world, x, y, z, stack));
	    }
	 
	 public static ItemStack consumeItem(ItemStack stack)
	    {
	        return consumeItem(stack, 1);
	    }

	    public static ItemStack consumeItem(ItemStack stack, int amount)
	    {
	        if (stack.getCount() > amount)
	        {
	            stack.shrink(amount);
	            return stack;
	        }
	        return ItemStack.EMPTY;
	    }

	    public static ItemStack consumeItem(EntityPlayer player, ItemStack stack)
	    {
	        return player.isCreative() ? stack : consumeItem(stack, 1);
	    }

	    public static ItemStack consumeItem(EntityPlayer player, ItemStack stack, int amount)
	    {
	        return player.isCreative() ? stack : consumeItem(stack, amount);
	    }
}

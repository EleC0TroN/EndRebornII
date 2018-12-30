package endreborn.mod.items;

import endreborn.init.ItemInit;
import endreborn.EndReborn;
import endreborn.utils.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWorldMirror extends Item implements IHasModel
{
    public ItemWorldMirror(String name)
    {
    	setUnlocalizedName(name);
    	setRegistryName(name);
    	this.maxStackSize = 1;
    	setCreativeTab(EndReborn.endertab);
    	
    	ItemInit.ITEMS.add(this);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn,
            EntityPlayer playerIn, EnumHand handIn )
    {
        ItemStack mainhand = playerIn.getHeldItemMainhand();
        {
            mainhand.damageItem(1, playerIn);
            playerIn.getCooldownTracker().setCooldown(this, 1000);
            BlockPos blockpos = worldIn.provider.getRandomizedSpawnPoint();
            playerIn.setPositionAndUpdate(blockpos.getX(), blockpos.getY(),
                    blockpos.getZ());
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS,
                playerIn.getHeldItem(handIn));
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
	@Override
	public void registerModels() 
	{
		EndReborn.proxy.registerItemRenderer(this, 0, "inventory");
	}
	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
	    return EnumRarity.RARE;
	}
}

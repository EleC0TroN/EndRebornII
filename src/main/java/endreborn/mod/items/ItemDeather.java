package endreborn.mod.items;

import endreborn.init.ItemInit;
import endreborn.EndReborn;
import endreborn.utils.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;

public class ItemDeather extends ItemSword implements IHasModel
{
	public ItemDeather(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
    	setRegistryName(name);
    	setCreativeTab(EndReborn.endertab);
    	
    	ItemInit.ITEMS.add(this);
	}
	@Override
	public void registerModels() 
	{
		EndReborn.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
	    return EnumRarity.UNCOMMON;
	}
	
	@Override

	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {

		if(player.world.isRemote) {

			return false;
		}

		Vec3d dir = player.getPositionVector().subtract(target.getPositionVector());
		target.addVelocity(dir.x*0.3, dir.y*0.3, dir.z*0.3);
		if(!player.capabilities.isCreativeMode) {
			stack.damageItem(1, player);
		}
		return true;
	}
}
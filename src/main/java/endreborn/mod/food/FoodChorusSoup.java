package endreborn.mod.food;

import endreborn.init.ItemInit;
import endreborn.EndReborn;
import endreborn.utils.IHasModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class FoodChorusSoup extends ItemFood implements IHasModel
{
	
    public FoodChorusSoup(int healAmount, String name)
    {
        super(healAmount, false);
		setUnlocalizedName(name);
    	setRegistryName(name);
        this.setMaxStackSize(1);
    	setCreativeTab(EndReborn.endertab);
    	setPotionEffect(new PotionEffect(MobEffects.GLOWING, 100, 0), 0.6F);
    	
    	ItemInit.ITEMS.add(this);
    }
	@Override
	public void registerModels() 
	{
		EndReborn.proxy.registerItemRenderer(this, 0, "inventory");
	}
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(Items.BOWL);
    }
}

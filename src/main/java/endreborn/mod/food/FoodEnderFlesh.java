package endreborn.mod.food;

import endreborn.init.ItemInit;
import endreborn.EndReborn;
import endreborn.utils.IHasModel;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;


public class FoodEnderFlesh extends ItemFood implements IHasModel
{
	public FoodEnderFlesh(String name) 
	{
		super(4, 0.4F, false);
		setUnlocalizedName(name);
    	setRegistryName(name);
    	setCreativeTab(EndReborn.endertab);
    	setPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 100, 1), 0.6F);
    	ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() 
	{
		EndReborn.proxy.registerItemRenderer(this, 0, "inventory");
	}

}


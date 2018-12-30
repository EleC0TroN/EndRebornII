package endreborn.mod.items;

import endreborn.init.ItemInit;
import endreborn.EndReborn;
import endreborn.utils.IHasModel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHammer extends Item implements IHasModel
{
    public ItemHammer(String name)
    {
    	setUnlocalizedName(name);
    	setRegistryName(name);
    	setCreativeTab(EndReborn.endertab);
    	setMaxStackSize(1);
    	
    	ItemInit.ITEMS.add(this);
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) 
    {
    	return true;
    }
    
    @Override
    public ItemStack getContainerItem(ItemStack stack) 
    {
		return new ItemStack(ItemInit.HAMMER_IRON);
        
    }
    
	@Override
	public void registerModels() 
	{
		EndReborn.proxy.registerItemRenderer(this, 0, "inventory");
	}
}

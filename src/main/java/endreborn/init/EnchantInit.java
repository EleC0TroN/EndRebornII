package endreborn.init;

import endreborn.mod.enchants.EnchantECore;
import endreborn.mod.enchants.EnchantSCore;
import endreborn.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class EnchantInit {
	public static final Enchantment ender_core = new EnchantECore();

	public static final Enchantment shulker_core = new EnchantSCore();
	
	@Mod.EventBusSubscriber(modid = Reference.MODID)
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void onEvent(final RegistryEvent.Register<Enchantment> event)
        {
            // DEBUG
            System.out.println("Registering Enchantments");

            final IForgeRegistry<Enchantment> registry = event.getRegistry();
            
            registry.register(ender_core);
            registry.register(shulker_core);
        }
    }
	
	public static final Enchantment[] helmetEnchants = new Enchantment[] {Enchantments.THORNS, Enchantments.AQUA_AFFINITY, ender_core};
	public static final Enchantment[] chestplateEnchants = new Enchantment[] {Enchantments.THORNS, ender_core};
	public static final Enchantment[] leggingsEnchants = new Enchantment[] {Enchantments.THORNS, ender_core};
	public static final Enchantment[] bootsEnchants = new Enchantment[] {Enchantments.THORNS, Enchantments.FROST_WALKER, Enchantments.DEPTH_STRIDER, ender_core};
	
	public static final Enchantment[] swordEnchants = new Enchantment[] {Enchantments.FIRE_ASPECT, shulker_core};
}

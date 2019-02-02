package endreborn.handlers;

import endreborn.init.BlockInit;
import endreborn.init.ItemInit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

public class ThaumHandler {
    private final AspectEventProxy proxy;

    private ThaumHandler(AspectEventProxy proxy) {
        this.proxy = proxy;
    }

    @SubscribeEvent
    public static void registerAspects(AspectRegistryEvent event) {
        ThaumHandler handler = new ThaumHandler(event.register);
        handler.registerAllAspects();

    }

    private void registerAllAspects() {
        {

            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.DEATH_ESSENCE), new AspectList().add(Aspect.ENTROPY, 15)
                    .add(Aspect.ORDER, 3));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.INGOT_WOLFRAMIUM), new AspectList().add(Aspect.METAL, 15));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.ENDER_STRING), new AspectList().add(Aspect.DEATH, 5)
                .add(Aspect.LIFE, 5));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.DRAGON_SCALES), new AspectList().add(Aspect.BEAST, 8)
                    .add(Aspect.ELDRITCH, 5));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.END_SHARD), new AspectList().add(Aspect.CRYSTAL, 5)
                    .add(Aspect.DARKNESS, 3));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.LORMYTE_CRYSTAL), new AspectList().add(Aspect.LIGHT, 5)
                    .add(Aspect.CRYSTAL, 2));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.ASH), new AspectList().add(Aspect.FIRE, 1));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.CATALYST), new AspectList().add(Aspect.EARTH, 1)
                    .add(Aspect.FIRE, 1).add(Aspect.DARKNESS, 1));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.DRAGONITE_BERRIES), new AspectList().add(Aspect.PLANT, 1)
                    .add(Aspect.DARKNESS, 2).add(Aspect.BEAST, 1));
            ThaumcraftApi.registerObjectTag(new ItemStack(ItemInit.CHORUS_SOUP), new AspectList().add(Aspect.PLANT, 1)
                    .add(Aspect.DARKNESS, 2).add(Aspect.BEAST, 1));
            ThaumcraftApi.registerObjectTag(new ItemStack(BlockInit.BLOCK_ENDORIUM), new AspectList().add(Aspect.DARKNESS, 105)
                    .add(Aspect.MOTION, 98));
            ThaumcraftApi.registerObjectTag(new ItemStack(BlockInit.BLOCK_END_MAGMA), new AspectList().add(Aspect.EARTH, 6)
                    .add(Aspect.FIRE, 10).add(Aspect.DARKNESS, 8));
            ThaumcraftApi.registerObjectTag(new ItemStack(BlockInit.BLOCK_ASH), new AspectList().add(Aspect.FIRE, 2));
            ThaumcraftApi.registerObjectTag(new ItemStack(BlockInit.BLOCK_WOLFRAMIUM), new AspectList().add(Aspect.METAL, 135));
            ThaumcraftApi.registerObjectTag(new ItemStack(BlockInit.BLOCK_WOLFRAMIUM), new AspectList().add(Aspect.METAL, 13));
            ThaumcraftApi.registerObjectTag(new ItemStack(BlockInit.BLOCK_E_USER), new AspectList().add(Aspect.DARKNESS, 13)
                    .add(Aspect.MECHANISM, 15).add(Aspect.ENTROPY, 13));
            ThaumcraftApi.registerObjectTag(new ItemStack(BlockInit.DRAGON_ESSENCE), new AspectList().add(Aspect.ENTROPY, 12));
            ThaumcraftApi.registerObjectTag(new ItemStack(BlockInit.TECH_PORTAL), new AspectList().add(Aspect.EARTH, 12)
                    .add(Aspect.ELDRITCH, 12));
        }
    }
}

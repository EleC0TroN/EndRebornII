package endreborn.handlers;

import endreborn.init.BlockInit;
import endreborn.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

public class AspectHandler {
    // Use the thaumcraft API to register our things with aspects and biomes with values
@SubscribeEvent
public static void registerAspects(AspectRegistryEvent event) {

        try {
        TFAspectRegisterHelper helper = new TFAspectRegisterHelper(event);


            helper.registerTCObjectTag(BlockInit.BLOCK_WOLFRAMIUM, new AspectList()
                    .add(Aspect.METAL, 135));
            helper.registerTCObjectTag(ItemInit.END_ESSENCE, new AspectList()
                    .add(Aspect.EARTH, 5)
                    .add(Aspect.DARKNESS, 3));
            helper.registerTCObjectTag(ItemInit.DEATH_ESSENCE, new AspectList()
                    .add(Aspect.ENTROPY, 15)
                    .add(Aspect.ORDER, 3));
            helper.registerTCObjectTag(ItemInit.ENDER_STRING, new AspectList()
                    .add(Aspect.DEATH, 5)
                    .add(Aspect.LIFE, 5));
            helper.registerTCObjectTag(ItemInit.DRAGON_SCALES, new AspectList()
                    .add(Aspect.BEAST, 8)
                    .add(Aspect.ELDRITCH, 5));
            helper.registerTCObjectTag(ItemInit.END_SHARD, new AspectList()
                    .add(Aspect.CRYSTAL, 5)
                    .add(Aspect.DARKNESS, 3));
            helper.registerTCObjectTag(ItemInit.LORMYTE_CRYSTAL, new AspectList()
                    .add(Aspect.LIGHT, 5)
                    .add(Aspect.CRYSTAL, 2));
            helper.registerTCObjectTag(ItemInit.ASH, new AspectList()
                    .add(Aspect.FIRE, 1));
            helper.registerTCObjectTag(ItemInit.CATALYST, new AspectList()
                    .add(Aspect.EARTH, 1)
                    .add(Aspect.FIRE, 1)
                    .add(Aspect.DARKNESS, 1));
            helper.registerTCObjectTag(ItemInit.CHORUS_SOUP, new AspectList()
                    .add(Aspect.PLANT, 1)
                    .add(Aspect.DARKNESS, 2)
                    .add(Aspect.BEAST, 1));
            helper.registerTCObjectTag(ItemInit.DRAGONITE_BERRIES, new AspectList()
                    .add(Aspect.PLANT, 1)
                    .add(Aspect.DARKNESS, 2)
                    .add(Aspect.BEAST, 1));
            helper.registerTCObjectTag(BlockInit.BLOCK_END_MAGMA, new AspectList()
                    .add(Aspect.EARTH, 6)
                    .add(Aspect.FIRE, 10)
                    .add(Aspect.DARKNESS, 8));
            helper.registerTCObjectTag(BlockInit.BLOCK_ASH, new AspectList()
                    .add(Aspect.FIRE, 2));
            helper.registerTCObjectTag(BlockInit.BLOCK_E_USER, new AspectList()
                    .add(Aspect.DARKNESS, 13)
                    .add(Aspect.MECHANISM, 15)
                    .add(Aspect.ENTROPY, 13));
            helper.registerTCObjectTag(BlockInit.DRAGON_ESSENCE, new AspectList()
                    .add(Aspect.ENTROPY, 12));
            helper.registerTCObjectTag(BlockInit.TECH_PORTAL, new AspectList()
                    .add(Aspect.EARTH, 12)
                    .add(Aspect.ELDRITCH, 12));
            helper.registerTCObjectTag(BlockInit.ORE_WOLFRAMIUM, new AspectList()
                    .add(Aspect.METAL, 13));
            helper.registerTCObjectTag(BlockInit.BLOCK_ENDORIUM, new AspectList()
                    .add(Aspect.DARKNESS, 105)
                    .add(Aspect.MOTION, 98));
            helper.registerTCObjectTag(BlockInit.ENTROPY_END_STONE, new AspectList()
                    .add(Aspect.EARTH, 13)
                    .add(Aspect.ENTROPY, 4));
            helper.registerTCObjectTag(BlockInit.ESSENCE_ORE, new AspectList()
                    .add(Aspect.EARTH, 7)
                    .add(Aspect.ENTROPY, 14));

        } catch (Exception e) {

        }
        }

private static class TFAspectRegisterHelper {

    private final AspectRegistryEvent event;

    private TFAspectRegisterHelper(AspectRegistryEvent event) {
        this.event = event;
    }

    private void registerTCObjectTag(Block block, AspectList list) {
        registerTCObjectTag(new ItemStack(block), list);
    }
    private void registerTCObjectTag(Item item, AspectList list) {
        registerTCObjectTag(new ItemStack(item), list);
    }


    private void registerTCObjectTag(ItemStack stack, AspectList list) {
        event.register.registerObjectTag(stack, list);
    }
}
}

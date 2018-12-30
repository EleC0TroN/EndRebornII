package endreborn.init;

import endreborn.EndReborn;
import endreborn.Reference;
import endreborn.mod.entity.EntityWatcher;
import endreborn.mod.entity.render.RenderAngryEnder;
import endreborn.mod.entity.render.RenderEGuard;
import endreborn.mod.entity.render.RenderLord;
import endreborn.mod.entity.render.RenderWatcher;
import endreborn.mod.entity.EntityAngryEnder;
import endreborn.mod.entity.EntityEGuard;
import endreborn.mod.entity.EntityLord;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitiesInit {

    public static void init() {
        int id = 1;
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "endguard"), EntityEGuard.class, "endguard", id++, EndReborn.instance, 64, 3, false, 9654933, 11237052);
        EntityRegistry.addSpawn(EntityEGuard.class, 3, 0, 1, EnumCreatureType.MONSTER, Biomes.SKY);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "watcher"), EntityWatcher.class, "watcher", id++, EndReborn.instance, 64, 3, false, 461076, 2236447);
        EntityRegistry.addSpawn(EntityEndermite.class, 3, 0, 1, EnumCreatureType.MONSTER, Biomes.SKY);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "endlord"), EntityLord.class, "endlord", id++, EndReborn.instance, 64, 3, false, 461076, 681365);
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID, "angry_enderman"), EntityAngryEnder.class, "angry_enderman", id++, EndReborn.instance, 64, 3, false, 461076, 660033);

    }
    
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEGuard.class, RenderEGuard.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityWatcher.class, RenderWatcher.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityLord.class, RenderLord.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityAngryEnder.class, RenderAngryEnder.FACTORY);
    }
}


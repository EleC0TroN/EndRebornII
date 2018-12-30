package endreborn.mod.entity.render;

import endreborn.Reference;
import endreborn.mod.entity.EntityAngryEnder;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderAngryEnder extends RenderLiving<EntityAngryEnder>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/angry_enderman.png");
	
	public static final Factory FACTORY = new Factory();
	
	public RenderAngryEnder(RenderManager manager) 
	{
		super(manager, new ModelEnderman(0), 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityAngryEnder entity) 
	{
		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityAngryEnder entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
	public static class Factory implements IRenderFactory<EntityAngryEnder> {

        @Override
        public Render<? super EntityAngryEnder> createRenderFor(RenderManager manager) {
            return new RenderAngryEnder(manager);
        }
}
}



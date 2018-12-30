package endreborn.mod.entity.render;

import endreborn.Reference;
import endreborn.mod.entity.EntityLord;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderLord extends RenderLiving<EntityLord>
{
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/entity/endlord.png");
	
	public static final Factory FACTORY = new Factory();
	
	public RenderLord(RenderManager manager) 
	{
		super(manager, new ModelBiped(), 1.0F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLord entity) 
	{
		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityLord entityLiving, float p_77043_2_, float rotationYaw, float partialTicks)
	{
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}
	public static class Factory implements IRenderFactory<EntityLord> {

        @Override
        public Render<? super EntityLord> createRenderFor(RenderManager manager) {
            return new RenderLord(manager);
        }
}
}
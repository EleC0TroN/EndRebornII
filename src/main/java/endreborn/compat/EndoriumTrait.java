package endreborn.compat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class EndoriumTrait extends AbstractTrait {
    public EndoriumTrait() {
        super("endorium", TextFormatting.GRAY);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if (isCritical || random.nextInt(10) == 0)
            player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 60, isCritical ? 0 : 2));
    }
}
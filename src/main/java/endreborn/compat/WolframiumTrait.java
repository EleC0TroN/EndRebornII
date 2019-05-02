package endreborn.compat;

import endreborn.mod.entity.EntityAngryEnder;
import endreborn.mod.entity.EntityChronologist;
import endreborn.mod.entity.EntityWatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.shared.block.PropertyTableItem;

public class WolframiumTrait extends AbstractTrait {
    public WolframiumTrait() {
        super("wolframium", TextFormatting.GRAY);
    }

    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if (player.world.getCurrentMoonPhaseFactor() == 1.0F)
            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 256, 1));
    }
}
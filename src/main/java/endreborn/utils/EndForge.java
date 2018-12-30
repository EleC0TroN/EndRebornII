package endreborn.utils;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import endreborn.init.BlockInit;
import endreborn.init.ItemInit;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@ParametersAreNonnullByDefault
public final class EndForge
{
    public static boolean hasAction(World world, BlockPos pos, ItemStack stack, @Nullable EnumFacing face)
    {
        IBlockState state = world.getBlockState(pos);
        if (stack.getItem() == Item.getItemFromBlock(Blocks.IRON_ORE))
        {
            return state.getMaterial() == BlockInit.END_FORGE && state.isNormalCube() && face == EnumFacing.UP;
        }
        if (stack.getItem() == Item.getItemFromBlock(BlockInit.ORE_WOLFRAMIUM))
        {
            return state.getMaterial() == BlockInit.END_FORGE && state.isNormalCube() && face == EnumFacing.UP;
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_ORE))
        {
            return state.getMaterial() == BlockInit.END_FORGE && state.isNormalCube() && face == EnumFacing.UP;
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.DIAMOND_ORE))
        {
            return state.getMaterial() == BlockInit.END_FORGE && state.isNormalCube() && face == EnumFacing.UP;
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.QUARTZ_ORE))
        {
            return state.getMaterial() == BlockInit.END_FORGE && state.isNormalCube() && face == EnumFacing.UP;
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.EMERALD_ORE))
        {
            return state.getMaterial() == BlockInit.END_FORGE && state.isNormalCube() && face == EnumFacing.UP;
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.GOLD_ORE))
        {
            return state.getMaterial() == BlockInit.END_FORGE && state.isNormalCube() && face == EnumFacing.UP;
        }
        
        return false;
    }

    /**
     * Performs the action
     *
     * @return true if the event should be cancelled
     */
    public static boolean performAction(World world, BlockPos pos, EntityPlayer player, ItemStack stack, @Nullable EnumFacing face, EnumHand hand)
    {
        if (stack.getItem() == Item.getItemFromBlock(Blocks.IRON_ORE))
        {
            return handleForgerI(world, pos, player, stack, hand);
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.GOLD_ORE))
        {
            return handleForgerG(world, pos, player, stack, hand);
        }
        if (stack.getItem() == Item.getItemFromBlock(BlockInit.ORE_WOLFRAMIUM))
        {
            return handleForgerW(world, pos, player, stack, hand);
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_ORE))
        {
            return handleForgerR(world, pos, player, stack, hand);
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.DIAMOND_ORE))
        {
            return handleForgerD(world, pos, player, stack, hand);
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.QUARTZ_ORE))
        {
            return handleForgerQ(world, pos, player, stack, hand);
        }
        if (stack.getItem() == Item.getItemFromBlock(Blocks.EMERALD_ORE))
        {
            return handleForgerE(world, pos, player, stack, hand);
        }
        return false;
    }

    private static boolean handleForgerI(World world, BlockPos pos, EntityPlayer player, ItemStack stack, EnumHand hand)
    {
        if (!world.isRemote)
        {
            {
                {
                    EndHelper.dropItemInWorldExact(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.IRON_INGOT, 1));
                }
                player.setHeldItem(hand, EndHelper.consumeItem(player, stack));
            }
            world.playSound(null, pos, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }
    private static boolean handleForgerG(World world, BlockPos pos, EntityPlayer player, ItemStack stack, EnumHand hand)
    {
        if (!world.isRemote)
        {
            {
                {
                    EndHelper.dropItemInWorldExact(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.GOLD_INGOT, 1));
                }
                player.setHeldItem(hand, EndHelper.consumeItem(player, stack));
            }
            world.playSound(null, pos, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }
    private static boolean handleForgerW(World world, BlockPos pos, EntityPlayer player, ItemStack stack, EnumHand hand)
    {
        if (!world.isRemote)
        {
            {
                {
                    EndHelper.dropItemInWorldExact(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(ItemInit.INGOT_WOLFRAMIUM, 1));
                }
                player.setHeldItem(hand, EndHelper.consumeItem(player, stack));
            }
            world.playSound(null, pos, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }
    private static boolean handleForgerD(World world, BlockPos pos, EntityPlayer player, ItemStack stack, EnumHand hand)
    {
        if (!world.isRemote)
        {
            {
                {
                    EndHelper.dropItemInWorldExact(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.DIAMOND, 1));
                }
                player.setHeldItem(hand, EndHelper.consumeItem(player, stack));
            }
            world.playSound(null, pos, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }
    private static boolean handleForgerQ(World world, BlockPos pos, EntityPlayer player, ItemStack stack, EnumHand hand)
    {
        if (!world.isRemote)
        {
            {
                {
                    EndHelper.dropItemInWorldExact(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.QUARTZ, 1));
                }
                player.setHeldItem(hand, EndHelper.consumeItem(player, stack));
            }
            world.playSound(null, pos, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }
    private static boolean handleForgerR(World world, BlockPos pos, EntityPlayer player, ItemStack stack, EnumHand hand)
    {
        if (!world.isRemote)
        {
            {
                {
                    EndHelper.dropItemInWorldExact(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.REDSTONE, 1));
                }
                player.setHeldItem(hand, EndHelper.consumeItem(player, stack));
            }
            world.playSound(null, pos, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }
    private static boolean handleForgerE(World world, BlockPos pos, EntityPlayer player, ItemStack stack, EnumHand hand)
    {
        if (!world.isRemote)
        {
            {
                {
                    EndHelper.dropItemInWorldExact(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(Items.EMERALD, 1));
                }
                player.setHeldItem(hand, EndHelper.consumeItem(player, stack));
            }
            world.playSound(null, pos, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return true;
    }
}

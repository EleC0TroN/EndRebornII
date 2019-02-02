package endreborn.compat;

import endreborn.EndReborn;
import endreborn.Reference;
import endreborn.handlers.AspectHandler;
import endreborn.init.BlockInit;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.relauncher.Side;
public enum EndCompat {
    CHISEL("Chisel") {
        @Override
        public void init() {
            addBlockToCarvingGroup("endstone", new ItemStack(BlockInit.END_STONE_PILLAR));
            addBlockToCarvingGroup("endstone", new ItemStack(BlockInit.END_STONE_SMOOTH));
            addBlockToCarvingGroup("purpur", new ItemStack(BlockInit.TECH_PORTAL));
        }

        private void addBlockToCarvingGroup(String group, ItemStack stack) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("group", group);
            nbt.setTag("stack", stack.serializeNBT());
            FMLInterModComms.sendMessage(team.chisel.api.ChiselAPIProps.MOD_ID, team.chisel.api.IMC.ADD_VARIATION_V2.toString(), nbt);
        }
    },
    @SuppressWarnings("WeakerAccess")
    TCONSTRUCT("Tinkers' Construct") {
        @Override
        protected boolean preInit() {
            TConstruct.preInit();
            return true;
        }

        @Override
        protected void init() {
            TConstruct.init();
        }

        @Override
        protected void postInit() {
            TConstruct.postInit();
        }
    },
    THAUMCRAFT("Thaumcraft") {
        @Override
        protected boolean preInit() {
            MinecraftForge.EVENT_BUS.register(AspectHandler.class);
            return true;
        }
    };

    protected boolean preInit() { return true; }
    protected void init() {}
    protected void postInit() {}


    final private String modName;

    private boolean isActivated = false;

    public boolean isActivated() {
        return isActivated;
    }
    public static void preInitCompat() {
        for (EndCompat compat : EndCompat.values()) {
            if (Loader.isModLoaded(compat.name().toLowerCase())) {
                try {
                    compat.isActivated = compat.preInit();

                    if (compat.isActivated()) {
                        EndReborn.LOGGER.info(Reference.MODID + " has loaded compatibility for mod " + compat.modName + ".");
                    } else {
                        EndReborn.LOGGER.info(Reference.MODID + " couldn't activate compatibility for mod " + compat.modName + "!");
                    }
                } catch (Exception e) {
                    compat.isActivated = false;
                    EndReborn.LOGGER.info(Reference.MODID + " had a " + e.getLocalizedMessage() + " error loading " + compat.modName + " compatibility!");
                    EndReborn.LOGGER.catching(e.fillInStackTrace());
                }
            } else {
                compat.isActivated = false;
                EndReborn.LOGGER.info(Reference.MODID + " has skipped compatibility for mod " + compat.modName + ".");
            }
        }
    }

    public static void initCompat() {
        for (EndCompat compat : EndCompat.values()) {
            if (compat.isActivated) {
                try {
                    compat.init();
                } catch (Exception e) {
                    compat.isActivated = false;
                    EndReborn.LOGGER.info(Reference.MODID + " had a " + e.getLocalizedMessage() + " error loading " + compat.modName + " compatibility in init!");
                    EndReborn.LOGGER.catching(e.fillInStackTrace());
                }
            }
        }
    }

    public static void postInitCompat() {
        for (EndCompat compat : EndCompat.values()) {
            if (compat.isActivated) {
                try {
                    compat.postInit();
                } catch (Exception e) {
                    compat.isActivated = false;
                    EndReborn.LOGGER.info(Reference.MODID + " had a " + e.getLocalizedMessage() + " error loading " + compat.modName + " compatibility in postInit!");
                    EndReborn.LOGGER.catching(e.fillInStackTrace());
                }
            }
        }
    }
    EndCompat(String modName) {
        this.modName = modName;
    }

    static void registerSidedHandler(Side side, Object handler) {
        if (FMLCommonHandler.instance().getSide() == side) {
            MinecraftForge.EVENT_BUS.register(handler);
        }
    }
}

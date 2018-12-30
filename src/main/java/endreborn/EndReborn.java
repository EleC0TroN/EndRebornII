package endreborn;

import java.io.File;

import endreborn.handlers.ConfigHandler;
import endreborn.handlers.EndVillagerHandler;
import endreborn.handlers.RegistryHandler;
import endreborn.init.RecipesInit;
import endreborn.proxy.CommonProxy;
import endreborn.utils.GuiMainMenuEnd;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class EndReborn 
{
	public static File config;
	public static final CreativeTabs endertab = new EndRebornTab("endertab");
	
    public static boolean activateEndGeneration;
    public static boolean activateVanillaEndOres;
	
    
	@Instance(Reference.MODID)
	public static EndReborn mod;
	
	@Instance
	public static EndReborn instance;

    public static EndReborn getInstance()
    {
        return instance;
    }
	
	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.COMMONPROXY)
	public static CommonProxy proxy;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		RegistryHandler.preInitRegistries(event);
    }

    @EventHandler
    public static void init(FMLInitializationEvent event)
    {
    	if(event.getSide() == Side.CLIENT) 
        {
    	EndVillagerHandler.initIEVillagerTrades();
    	EndVillagerHandler.initIEVillagerHouse();
        }
    	RecipesInit.init();
    	
        if(event.getSide() == Side.CLIENT && ConfigHandler.panorama) 
        {
            GuiMainMenuEnd.endMainMenu();
        }
    }
}


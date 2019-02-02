package endreborn.handlers;

import endreborn.init.ItemInit;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHandler {

    public static void registerOres() {
        OreDictionary.registerOre("ingotWolfram", ItemInit.INGOT_WOLFRAMIUM);
        OreDictionary.registerOre("dustAsh", ItemInit.ASH);
        OreDictionary.registerOre("dustObsidian", ItemInit.CATALYST);
        OreDictionary.registerOre("hammer", ItemInit.HAMMER_IRON);
    }

}

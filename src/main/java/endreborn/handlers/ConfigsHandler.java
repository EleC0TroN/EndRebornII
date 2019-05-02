package endreborn.handlers;

import endreborn.Reference;
import net.minecraftforge.common.config.Config;

@SuppressWarnings("WeakerAccess")
@Config(modid = Reference.MODID, category = "")
public final class ConfigsHandler
{
    public static final GeneralConfig GENERAL = new GeneralConfig();
    public static final BalanceConfig BALANCE = new BalanceConfig();

    public static class GeneralConfig
    {
        @Config.Name("Essence Ore")
        @Config.RequiresMcRestart
        @Config.Comment({"Allows to spawn."})
        public boolean spawnEssenceOre = true;

        @Config.Name("Wolframium Ore")
        @Config.RequiresMcRestart
        @Config.Comment({"Allows to spawn."})
        public boolean spawnWolframiumOre = true;

        @Config.Name("End Islands")
        @Config.RequiresMcRestart
        @Config.Comment({"Allows to spawn."})
        public boolean spawnEndIsland = true;

        @Config.Name("Observatory")
        @Config.RequiresMcRestart
        @Config.Comment({"Allows to spawn."})
        public boolean spawnObservatory = true;

        @Config.Name("Lormyte")
        @Config.RequiresMcRestart
        @Config.Comment({"Allows to spawn."})
        public boolean spawnLormyte = true;

        @Config.Name("End Magma, Enropy End Stone")
        @Config.RequiresMcRestart
        @Config.Comment({"Allows to spawn."})
        public boolean decoratorEnd = true;

        @Config.Name("Chest Loot")
        @Config.RequiresMcRestart
        @Config.Comment({"Allows to fill."})
        public boolean chestLoot = true;

        @Config.Name("Panorama")
        @Config.RequiresMcRestart
        @Config.Comment({"Panorama. Hmmm..."})
        public boolean panorama = true;

        @Config.Name("End Void Teleporter")
        @Config.RequiresMcRestart
        @Config.Comment({"Do you really need a comment?"})
        public boolean teleporterEnd = true;

        private GeneralConfig() {}
    }

    public static class BalanceConfig
    {

        @Config.Name("Island Rarity")
        @Config.RangeInt(min = 0, max = 1000)
        @Config.Comment({"If <200 That Island More Rare"})
        public int islandRare = 200;

        @Config.Name("Portal Rarity")
        @Config.RangeInt(min = 0, max = 2000)
        @Config.Comment({"If <1000 That Portal More Rare"})
        public int portalRare = 1000;

        @Config.Name("Essence Rarity In The End")
        @Config.RangeInt(min = 0, max = 1000)
        @Config.Comment({"If <100 That Essence More Rare"})
        public int essenceRareEnd = 100;

        @Config.Name("Essence Rarity In Overworld")
        @Config.RangeInt(min = 0, max = 1000)
        @Config.Comment({"If <50 That Essence More Rare"})
        public int essenceRareOver = 50;

        @Config.Name("Wolframium Rarity")
        @Config.RangeInt(min = 0, max = 1000)
        @Config.Comment({"If <50 That Wolframium More Rare"})
        public int wolframiumRare = 25;

        @Config.Name("Angry Ender Spawn Rarity")
        @Config.RangeInt(min = 1, max = 1000)
        @Config.Comment({"Chance to spawn = 1/(this number). Configuration, you exist."})
        public int angryendRare = 100;

        @Config.Name("Watcher Spawn Rarity")
        @Config.RangeInt(min = 1, max = 1000)
        @Config.Comment({"Chance to spawn = 1/(this number). Its a math!"})
        public int watcherRare = 50;

        @Config.Name("End Guard Spawn Rarity")
        @Config.RangeInt(min = 0, max = 100)
        @Config.Comment({"If >3 That Spawn More Rare"})
        public int guardRare = 3;

        @Config.Name("Endermite Spawn Rarity")
        @Config.RangeInt(min = 0, max = 100)
        @Config.Comment({"If >3 That Spawn More Rare"})
        public int endermiteRare = 3;

        private BalanceConfig() {}
    }
}

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
        @Config.Comment({"Panorama."})
        public boolean panorama = true;

        @Config.Name("End Void Teleporter")
        @Config.RequiresMcRestart
        @Config.Comment({"End Void Teleporter."})
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

        private BalanceConfig() {}
    }
}

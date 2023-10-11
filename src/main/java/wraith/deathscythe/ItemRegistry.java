package wraith.deathscythe;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

public class ItemRegistry {

    private static final HashMap<String, Item> ITEMS = new HashMap<>();

    private static void registerItem(String id, Item item) {
        ITEMS.put(id, Registry.register(Registry.ITEM, Utils.ID(id), item));
    }

    public static void init() {
        if (!ITEMS.isEmpty()) {
            return;
        }
        registerItem("reapers_scythe", new Item(new FabricItemSettings().fireproof().group(ItemGroup.COMBAT).maxCount(1)));

    }

    public static Item get(String id) {
        return ITEMS.get(id);
    }

}

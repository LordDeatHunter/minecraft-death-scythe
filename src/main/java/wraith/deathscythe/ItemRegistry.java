package wraith.deathscythe;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.HashMap;

public class ItemRegistry {

    private static final HashMap<String, Item> ITEMS = new HashMap<>();

    private static void registerItem(String id, Item item) {
        ITEMS.put(id, Registry.register(Registries.ITEM, Utils.ID(id), item));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(itemGroup -> itemGroup.add(item));
    }

    public static void init() {
        if (!ITEMS.isEmpty()) {
            return;
        }
        registerItem("reapers_scythe", new Item(new FabricItemSettings().fireproof().maxCount(1)));
    }

    public static Item get(String id) {
        return ITEMS.get(id);
    }

}

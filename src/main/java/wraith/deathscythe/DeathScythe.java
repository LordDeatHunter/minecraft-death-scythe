package wraith.deathscythe;

import net.fabricmc.api.ModInitializer;

public class DeathScythe implements ModInitializer {

    @Override
    public void onInitialize() {
        ItemRegistry.init();
    }
}

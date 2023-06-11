package io.github.steveplays28.sodiumrenderdistanceunlocker;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class SodiumRenderDistanceUnlocker implements ClientModInitializer {
	public static final String MOD_NAME = "Sodium Render Distance Unlocker";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static int MAX_RENDER_DISTANCE = 64;
	public static int MAX_SIMULATION_DISTANCE = 64;

	@Override
	public void onInitializeClient() {
		LOGGER.info("Extending the maximum render/simulation distance!");
	}
}

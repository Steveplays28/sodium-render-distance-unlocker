package io.github.steveplays28.sodiumrenderdistanceunlocker.mixin;

import io.github.steveplays28.sodiumrenderdistanceunlocker.SodiumRenderDistanceUnlocker;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptionPages;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = SodiumGameOptionPages.class, remap = false)
public abstract class SRDUSodiumGameOptionPagesMixin {
	@ModifyConstant(method = "lambda$general$0", constant = @Constant(intValue = 32))
	private static int srduMaxRenderDistance(int oldValue) {
		return SodiumRenderDistanceUnlocker.MAX_RENDER_DISTANCE;
	}

	@ModifyConstant(method = "lambda$general$3", constant = @Constant(intValue = 32))
	private static int srduMaxSimulationDistance(int oldValue) {
		return SodiumRenderDistanceUnlocker.MAX_SIMULATION_DISTANCE;
	}
}

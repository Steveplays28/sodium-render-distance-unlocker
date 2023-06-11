package io.github.steveplays28.sodiumrenderdistanceunlocker.mixin;

import io.github.steveplays28.sodiumrenderdistanceunlocker.SodiumRenderDistanceUnlocker;
import net.minecraft.server.integrated.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(IntegratedServer.class)
public class SRDUIntegratedServerMixin {
	@ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(II)I"), index = 1)
	private int bobbyViewDistanceOverwrite(int viewDistance) {
		return SodiumRenderDistanceUnlocker.MAX_SIMULATION_DISTANCE;
	}
}

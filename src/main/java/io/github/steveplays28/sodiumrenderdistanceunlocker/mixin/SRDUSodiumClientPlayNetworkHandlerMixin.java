package io.github.steveplays28.sodiumrenderdistanceunlocker.mixin;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.UnloadChunkS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class SRDUSodiumClientPlayNetworkHandlerMixin {
	@Mixin(value = ClientPlayNetworkHandler.class, priority = 900)
	// Priority is lower than Sodium so we get to return before it runs
	public abstract static class SodiumClientPlayNetworkHandlerMixin {
		@Inject(method = "onUnloadChunk", at = @At("RETURN"), cancellable = true)
		private void dontReturnChunksAboveCustomRenderDistance(UnloadChunkS2CPacket packet, CallbackInfo ci) {
			ci.cancel(); // Bypass Sodium's unload hook
			// TODO: Implement some kind of check to see if the chunk does need to be unloaded. This might create a memory leak in its current state.
		}
	}
}

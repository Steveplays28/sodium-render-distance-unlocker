package io.github.steveplays28.sodiumrenderdistanceunlocker.mixin;

import io.github.steveplays28.sodiumrenderdistanceunlocker.SodiumRenderDistanceUnlocker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameOptions.class)
public abstract class SRDUGameOptionsMixin {
	@Mutable
	@Shadow
	private @Final SimpleOption<Integer> viewDistance;

	@Mutable
	@Shadow
	@Final
	private SimpleOption<Integer> simulationDistance;

	@Shadow
	public static Text getGenericValueText(Text prefix, Text value) {
		return null;
	}

	@Inject(method = "getClampedViewDistance", at = @At("HEAD"), cancellable = true)
	private void forceClientDistanceWhenBobbyIsActive(CallbackInfoReturnable<Integer> ci) {
		ci.setReturnValue(this.viewDistance.getValue());
	}

	@Inject(method = "load", at = @At("HEAD"))
	public void onInit(CallbackInfo ci) {
		this.viewDistance = new SimpleOption<>("options.renderDistance", SimpleOption.emptyTooltip(),
				(optionText, value) -> getGenericValueText(optionText, Text.translatable("options.chunks", value)),
				new SimpleOption.ValidatingIntSliderCallbacks(2, SodiumRenderDistanceUnlocker.MAX_RENDER_DISTANCE), 8,
				(value) -> MinecraftClient.getInstance().worldRenderer.scheduleTerrainUpdate()
		);

		this.simulationDistance = new SimpleOption<>("options.simulationDistance", SimpleOption.emptyTooltip(),
				(optionText, value) -> getGenericValueText(optionText, Text.translatable("options.chunks", value)),
				new SimpleOption.ValidatingIntSliderCallbacks(2, SodiumRenderDistanceUnlocker.MAX_SIMULATION_DISTANCE), 8, (value) -> {
		}
		);
	}
}

package paulevs.extremeview.mixin.client;

import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Option.class)
public class OptionMixin {
	@Inject(method = "isSlider", at = @At("HEAD"), cancellable = true)
	public void extremeview_setSlider(CallbackInfoReturnable<Boolean> info) {
		if (Option.class.cast(this) == Option.RENDER_DISTANCE) info.setReturnValue(true);
	}
}

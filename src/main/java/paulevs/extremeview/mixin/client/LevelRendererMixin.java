package paulevs.extremeview.mixin.client;

import net.minecraft.client.render.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulevs.extremeview.ExtremeView;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Shadow private int sectionCounX;
	@Shadow private int sectionCounZ;
	
	@Inject(method = "updateFromOptions", at = @At(
		value = "FIELD",
		target = "Lnet/minecraft/client/render/LevelRenderer;sectionCounZ:I",
		shift = Shift.AFTER,
		ordinal = 0
	))
	private void extremeview_updateDistance(CallbackInfo info) {
		this.sectionCounX = ExtremeView.getChunkSide();
		this.sectionCounZ = this.sectionCounX;
	}
}

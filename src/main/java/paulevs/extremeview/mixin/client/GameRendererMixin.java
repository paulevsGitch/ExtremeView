package paulevs.extremeview.mixin.client;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulevs.extremeview.ExtremeView;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@Shadow private float fogDistance;
	
	@Inject(method = "updateCameraAndFog", at = @At(
		value = "FIELD",
		target = "Lnet/minecraft/client/render/GameRenderer;fogDistance:F",
		shift = Shift.AFTER,
		ordinal = 0
	))
	private void extremeview_updateFogDistance(float delta, int par2, CallbackInfo info) {
		this.fogDistance = ExtremeView.getBlockRadius();
	}
}

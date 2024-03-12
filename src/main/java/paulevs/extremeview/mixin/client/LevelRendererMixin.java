package paulevs.extremeview.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.render.LevelRenderer;
import net.minecraft.entity.living.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
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
	
	@Inject(method = "updateOcclusion(Lnet/minecraft/entity/living/LivingEntity;ID)I", at = @At(
		value = "INVOKE",
		target = "Lnet/minecraft/client/render/LevelRenderer;updateOcclusion(II)V",
		shift = Shift.BEFORE,
		ordinal = 0
	))
	private void extremeview_updateOcclusion(LivingEntity entity, int d, double par3, CallbackInfoReturnable<Integer> info, @Local(index = 19) LocalIntRef stride) {
		if (this.sectionCounX == 1) stride.set(8);
	}
}

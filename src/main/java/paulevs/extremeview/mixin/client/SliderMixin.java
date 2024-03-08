package paulevs.extremeview.mixin.client;

import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.gui.widgets.Slider;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulevs.extremeview.ExtremeView;

@Mixin(Slider.class)
public abstract class SliderMixin extends Button {
	public SliderMixin(int i, int j, int k, String string) {
		super(i, j, k, string);
	}
	
	@Inject(method = "mouseReleased", at = @At("HEAD"))
	private void extremeview_onRelease(int j, int par2, CallbackInfo info) {
		if (id == Option.RENDER_DISTANCE.getId()) {
			ExtremeView.getMinecraft().levelRenderer.updateFromOptions();
		}
	}
}

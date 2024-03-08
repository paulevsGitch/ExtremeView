package paulevs.extremeview.mixin.client;

import net.minecraft.client.gui.screen.menu.VideoSettingsScreen;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoSettingsScreen.class)
public class VideoSettingScreenMixin {
	@Inject(method = "buttonClicked", at = @At("HEAD"), cancellable = true)
	private void extremeview_buttonClicked(Button button, CallbackInfo info) {
		if (button.id == Option.RENDER_DISTANCE.getId()) info.cancel();
	}
}

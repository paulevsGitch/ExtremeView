package paulevs.extremeview.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import paulevs.extremeview.ExtremeView;

import java.io.BufferedReader;
import java.io.PrintWriter;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
	@Unique private static final StringBuffer EXTREME_VIEW_BUFFER = new StringBuffer(256);
	@Unique private float extremeview_distance = 0.125F;
	
	@Shadow protected abstract float parseFloat(String string);
	@Shadow protected Minecraft minecraft;
	
	@Inject(method = "setFloat", at = @At("HEAD"), cancellable = true)
	private void extremeview_setFloat(Option option, float value, CallbackInfo info) {
		if (option == Option.RENDER_DISTANCE) {
			extremeview_distance = value;
			info.cancel();
		}
	}
	
	@Inject(method = "getFloatValue", at = @At("HEAD"), cancellable = true)
	private void extremeview_getFloat(Option option, CallbackInfoReturnable<Float> info) {
		if (option == Option.RENDER_DISTANCE) {
			info.setReturnValue(extremeview_distance);
		}
	}
	
	@Inject(method = "getTranslatedValue", at = @At("HEAD"), cancellable = true)
	private void extremeview_getTranslated(Option option, CallbackInfoReturnable<String> info) {
		if (option == Option.RENDER_DISTANCE) {
			EXTREME_VIEW_BUFFER.setLength(0);
			EXTREME_VIEW_BUFFER.append("View: ");
			EXTREME_VIEW_BUFFER.append(ExtremeView.getBlockRadius());
			EXTREME_VIEW_BUFFER.append(" Blocks");
			info.setReturnValue(EXTREME_VIEW_BUFFER.toString());
		}
	}
	
	@Inject(method = "load", at = @At(
		value = "INVOKE",
		target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z",
		shift = Shift.AFTER
	), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void extremeview_onLoad(CallbackInfo info, BufferedReader reader, String string, String[] optionPair) {
		if (optionPair[0].equals("extremeViewDistance")) {
			extremeview_distance = parseFloat(optionPair[1]);
		}
	}
	
	@Inject(method = "saveOptions", at = @At(
		value = "INVOKE",
		target = "Ljava/io/PrintWriter;println(Ljava/lang/String;)V",
		ordinal = 0
	), locals = LocalCapture.CAPTURE_FAILSOFT)
	private void extremeview_onSave(CallbackInfo info, PrintWriter printWriter) {
		EXTREME_VIEW_BUFFER.setLength(0);
		EXTREME_VIEW_BUFFER.append("extremeViewDistance:");
		EXTREME_VIEW_BUFFER.append(extremeview_distance);
		printWriter.println(EXTREME_VIEW_BUFFER);
	}
}

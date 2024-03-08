package paulevs.extremeview;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.options.Option;

public class ExtremeView {
	public static int getChunkSide() {
		return Math.round(getOption() * 128) | 1;
	}
	
	public static int getBlockRadius() {
		return getChunkSide() << 3;
	}
	
	private static float getOption() {
		return getMinecraft().options.getFloatValue(Option.RENDER_DISTANCE);
	}
	
	@SuppressWarnings("deprecation")
	public static Minecraft getMinecraft() {
		return (Minecraft) FabricLoader.getInstance().getGameInstance();
	}
}

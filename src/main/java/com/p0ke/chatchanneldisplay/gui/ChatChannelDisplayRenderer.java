package com.p0ke.chatchanneldisplay.gui;

import com.p0ke.chatchanneldisplay.ChatChannelDisplayMod;
import com.p0ke.chatchanneldisplay.event.HypixelChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;

public class ChatChannelDisplayRenderer {
	Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (HypixelChecker.onHypixel() && event.type == RenderGameOverlayEvent.ElementType.TEXT
				&& (this.mc.currentScreen instanceof GuiChat)) { // field_71462_r
			ChatChannelDisplayMod.INSTANCE.getGui().render(getMouseX(), getMouseY());
		}
	}

	@SubscribeEvent
	public void onMouseEvent(GuiScreenEvent.MouseInputEvent.Pre event) {
		if (HypixelChecker.onHypixel() && event.gui instanceof net.minecraft.client.gui.GuiChat
				&& Mouse.getEventButton() == 0 && Mouse.isButtonDown(0)) {
			ChatChannelDisplayMod.INSTANCE.getGui().onMouseClick(getMouseX(), getMouseY());
		}
	}

	private int getMouseX() {
		ScaledResolution sr = new ScaledResolution(this.mc);
		return Mouse.getX() * sr.getScaledWidth() / this.mc.displayWidth; // field_71443_c
	}

	private int getMouseY() {
		ScaledResolution sr = new ScaledResolution(this.mc);
		return sr.getScaledHeight() - Mouse.getY() * sr.getScaledHeight() / this.mc.displayHeight - 1; // field_71440_d
	}
}

package com.p0ke.chatchanneldisplay.util;

import net.minecraft.client.Minecraft;

public class CommandUtil {
	public static void send(String message) {
		(Minecraft.getMinecraft()).thePlayer.sendChatMessage(message); // field_71439_g.func_71165_d
	}
}

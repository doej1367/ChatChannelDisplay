package com.p0ke.chatchanneldisplay.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class HypixelChecker {
	private static boolean HYPIXEL = false;

	@SubscribeEvent
	public void joinServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		HYPIXEL = event.manager.getRemoteAddress().toString().toLowerCase().contains(".hypixel.net"); // func_74430_c
	}

	@SubscribeEvent
	public void leaveServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
		HYPIXEL = false;
	}

	public static boolean onHypixel() {
		return HYPIXEL;
	}
}

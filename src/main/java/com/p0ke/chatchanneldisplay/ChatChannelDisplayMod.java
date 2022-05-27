package com.p0ke.chatchanneldisplay;

import com.p0ke.chatchanneldisplay.event.ChatChannelMessageHandler;
import com.p0ke.chatchanneldisplay.event.HypixelChecker;
import com.p0ke.chatchanneldisplay.event.PartyChecker;
import com.p0ke.chatchanneldisplay.gui.ChatChannelDisplayGui;
import com.p0ke.chatchanneldisplay.gui.ChatChannelDisplayRenderer;
import com.p0ke.chatchanneldisplay.util.ChatChannelFileUtil;
import com.p0ke.chatchanneldisplay.util.CommandUtil;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "chatchanneldisplay", version = "2.1.1", acceptedMinecraftVersions = "[1.8.9]")
public class ChatChannelDisplayMod {
	public static final String MODID = "chatchanneldisplay";
	public static final String VERSION = "2.1.1";
	public static ChatChannelDisplayMod INSTANCE = null;

	private String mode = "";

	private ChatChannelDisplayGui gui;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		INSTANCE = this;

		ChatChannelFileUtil.loadFile(event.getModConfigurationDirectory().getAbsolutePath());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		this.mode = ChatChannelFileUtil.getMode();
		this.gui = new ChatChannelDisplayGui();

		MinecraftForge.EVENT_BUS.register(new ChatChannelMessageHandler());
		MinecraftForge.EVENT_BUS.register(new HypixelChecker());
		MinecraftForge.EVENT_BUS.register(new PartyChecker());
		MinecraftForge.EVENT_BUS.register(new ChatChannelDisplayRenderer());
	}

	public void setMode(String m) {
		this.mode = m;
		ChatChannelFileUtil.saveMode(this.mode);
	}

	public String getMode() {
		return this.mode;
	}

	public void cycleMode() {
		switch (this.mode) {
		case "GUILD":
			CommandUtil.send("/chat a");
			return;
		case "PARTY":
			CommandUtil.send("/chat g");
			return;
		case "ALL":
			if (PartyChecker.inParty()) {
				CommandUtil.send("/chat p");
			} else {
				CommandUtil.send("/chat g");
			}
			return;
		}
		CommandUtil.send("/chat a");
	}

	public ChatChannelDisplayGui getGui() {
		return this.gui;
	}
}

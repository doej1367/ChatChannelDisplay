package com.p0ke.chatchanneldisplay.event;

import com.p0ke.chatchanneldisplay.ChatChannelDisplayMod;
import com.p0ke.chatchanneldisplay.util.CommandUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ChatChannelMessageHandler {
	private static final String CHAT_MODE_SWITCH_MESSAGE = "You are now in the (\\w+) channel";
	private static final String ALL_CHAT_MESSAGE = "You are not in a (\\w+) and were moved to the ALL channel.";
	private static final String CHAT_EXPIRED_MESSAGE = "The conversation you were in expired and you have been moved back to the ALL channel.";
	private static final String PM_OPEN_MESSAGE = "Opened a chat conversation with .*?(\\w+) for the next 5 minutes. Use /chat a to leave";
	private static final String NO_GUILD_MESSAGE = "You must be in a guild to join the guild channel!";

	@SubscribeEvent(receiveCanceled = true)
	@SideOnly(Side.CLIENT)
	public void onMessage(ClientChatReceivedEvent event) {
		if (HypixelChecker.onHypixel()) {
			String message = event.message.getUnformattedText();

			Matcher chatModeMatcher = Pattern.compile(CHAT_MODE_SWITCH_MESSAGE).matcher(message);
			if (chatModeMatcher.matches()) {
				String mode = chatModeMatcher.group(1);
				ChatChannelDisplayMod.INSTANCE.setMode(mode);
			}
			if (Pattern.matches(ALL_CHAT_MESSAGE, message) || Pattern.matches(CHAT_EXPIRED_MESSAGE, message))
				ChatChannelDisplayMod.INSTANCE.setMode("ALL");
			if (Pattern.matches(PM_OPEN_MESSAGE, message))
				ChatChannelDisplayMod.INSTANCE.setMode("PM");
			if (Pattern.matches(NO_GUILD_MESSAGE, message))
				CommandUtil.send("/chat a");
		}
	}
}

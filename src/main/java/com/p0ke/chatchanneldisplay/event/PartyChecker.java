package com.p0ke.chatchanneldisplay.event;

import java.util.regex.Pattern;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PartyChecker {
	private static boolean PARTY = false;
	private static final String[] IN_PARTY = new String[] {
			".*?(\\w+) invited .*?(\\w+) to the party! They have 60 seconds to accept\\.",
			"You joined .*?(\\w+)'s party!", ".*?(\\w+) joined the party!", "Party > .*",
			"You are now in the PARTY channel", ".*?(\\w+) has been removed from your party", "Party members .*" };

	private static final String[] NOT_PARTIED = new String[] {
			"The party was disbanded because all invites have expired and all members have left\\.",
			".*?(\\w+) has disbanded the party!", "You left the party",
			"You must be in a party to join the party channel!",
			"You are not in a party and have been moved to the ALL channel!",
			"You must be in a party to use this command!", "You have been kicked from the party by .*?(\\w+)" };

	@SubscribeEvent
	public void onChatMessage(ClientChatReceivedEvent event) {
		String msg = event.message.getUnformattedText();

		for (String s : IN_PARTY) {
			if (Pattern.matches(s, msg)) {
				PARTY = true;

				return;
			}
		}
		for (String s : NOT_PARTIED) {
			if (Pattern.matches(s, msg)) {
				PARTY = false;
				return;
			}
		}
	}

	public static boolean inParty() {
		return PARTY;
	}
}

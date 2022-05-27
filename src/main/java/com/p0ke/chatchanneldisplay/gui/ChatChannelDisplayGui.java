package com.p0ke.chatchanneldisplay.gui;

import com.p0ke.chatchanneldisplay.ChatChannelDisplayMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ChatChannelDisplayGui extends Gui {
	private static final int VERTICAL_PADDING = 3;
	private static final int HORIZONTAL_PADDING = 4;
	private static final int HORIZONTAL_OFFSET = 10;
	private static final int VERTICAL_OFFSET = 20;
	private static final int BOX_COLOR = -2147483648;
	private static final int BOX_COLOR_HOVERED = -2130706433;
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	private boolean hovered;

	public void render(int mouseX, int mouseY) {
		Minecraft mc = Minecraft.getMinecraft(); // func_71410_x
		FontRenderer fr = mc.fontRendererObj; // field_71466_p

		String mode = ChatChannelDisplayMod.INSTANCE.getMode();
		int strWidth = fr.getStringWidth(mode); // func_78256_a
		int strHeight = fr.FONT_HEIGHT; // field_78288_b

		ScaledResolution res = new ScaledResolution(mc);
		int screenWidth = res.getScaledWidth(); // func_78326_a
		int screenHeight = res.getScaledHeight(); // func_78328_b

		GL11.glPushMatrix();

		this.xPosition = 2;
		this.yPosition = screenHeight - 20 - 6 - strHeight;
		this.width = 8 + strWidth - 1;
		this.height = 6 + strHeight - 1;

		this.hovered = (mouseX >= this.xPosition && mouseX <= this.xPosition + this.width && mouseY >= this.yPosition
				&& mouseY <= this.yPosition + this.height);

		drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height,
				this.hovered ? -2130706433 : Integer.MIN_VALUE); // func_73734_a

		GL11.glEnable(3042);
		drawString(fr, EnumChatFormatting.GOLD + mode, this.xPosition + 4, this.yPosition + 3, 16777215); // func_73731_b

		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}

	public void onMouseClick(int mouseX, int mouseY) {
		if (mouseX >= this.xPosition && mouseX <= this.xPosition + this.width && mouseY >= this.yPosition
				&& mouseY <= this.yPosition + this.height) {
			Minecraft.getMinecraft().getSoundHandler()
					.playSound((ISound) PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F)); // func_147118_V().func_147682_a(func_147674_a())
			ChatChannelDisplayMod.INSTANCE.cycleMode();
		}
	}
}

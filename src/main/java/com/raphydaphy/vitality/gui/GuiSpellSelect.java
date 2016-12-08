package com.raphydaphy.vitality.gui;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import com.google.common.collect.ImmutableSet;
import com.raphydaphy.vitality.init.KeyBindings;
import com.raphydaphy.vitality.init.ModItems;
import com.raphydaphy.vitality.item.ItemSpellBag;
import com.raphydaphy.vitality.item.ItemWand;
import com.raphydaphy.vitality.nbt.NBTLib;
import com.raphydaphy.vitality.network.PacketManager;
import com.raphydaphy.vitality.network.PacketSendKey;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GuiSpellSelect extends GuiScreen 
{
	public ItemStack getSpellStackFromID(int spellID)
	{
		switch (spellID)
		{
		case 80:
			return new ItemStack(ModItems.spell_card_vital_extraction);
		case 81:
			return new ItemStack(ModItems.spell_card_lantern);
		case 82:
			return new ItemStack(ModItems.spell_card_explosion);
		case 83:
			return new ItemStack(ModItems.spell_card_fireball);
		case 84:
			return new ItemStack(ModItems.spell_card_rapidfire);
		case 85:
			return new ItemStack(ModItems.spell_card_transmutation);
		case 86:
			return new ItemStack(ModItems.spell_card_hunger);
		case 87:
			return new ItemStack(ModItems.spell_card_enhanced_extraction);
		case 88:
			return new ItemStack(ModItems.spell_card_flight);
		case 89:
			return new ItemStack(ModItems.spell_card_forcefield);
		}
		return null;
	}

	int timeIn;
	int slotSelected = -1;
	
	int[] spellArray;
	int spellArrayLength;
	int activeSector = -1;
	
	ItemStack wandStack;
	ItemStack bagStack;

	@Override
	public void drawScreen(int mx, int my, float partialTicks) 
	{
		super.drawScreen(mx, my, partialTicks);
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		wandStack = player.getHeldItemMainhand();
		bagStack = player.getHeldItemOffhand();
		if (!(bagStack.getItem() instanceof ItemSpellBag))
		{
			bagStack = null;
		}
		if (!(wandStack.getItem() instanceof ItemWand))
		{
			wandStack = null;
		}
		
		try
		{
			spellArray = bagStack.getTagCompound().getIntArray("spells");
		}
		catch (NullPointerException e)
		{
			spellArray = null;
		}
		if (spellArray != null)
		{
			spellArrayLength = 0;
			for (int i = 0; i < spellArray.length; i++)
			{
				if (spellArray[i] != 0)
				{
					spellArrayLength++;
				}
			}
	
			float angle = -90;
			int radius = 75;
			int amount = spellArrayLength;
			
			int screenWidth = width / 2;
			int screenHeight = height / 2;
			
			// if any spells are held in the bag
			if (amount > 0)
			{
				float anglePer;
				if (NBTLib.getInt(bagStack, "selectedSpell", 0) != 0)
				{
					anglePer = 360F / (amount - 1);
				}
				else
				{
					anglePer = 360F / amount;
				}
	
				net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
				GlStateManager.pushMatrix();
				GuiHelper.renderCircle(screenWidth, screenHeight);
				GlStateManager.popMatrix();
				activeSector = -1;
				for (int curItem = 0; curItem < amount; curItem++)
				{
					if (spellArray[curItem] == NBTLib.getInt(bagStack, "selectedSpell", 0))
					{
						GlStateManager.pushMatrix();
						GlStateManager.translate(screenWidth - 16, screenHeight - 16, 0);
						GlStateManager.scale(2, 2, 2);
						if (getSpellStackFromID(NBTLib.getInt(bagStack, "selectedSpell", 0)) != null)
						{
							mc.getRenderItem().renderItemIntoGUI(getSpellStackFromID(NBTLib.getInt(bagStack, "selectedSpell", 0)), 0, 0);
						}
						GlStateManager.translate(-screenWidth, -screenHeight, 0);
						GlStateManager.popMatrix();
					}
					else
					{
						double xPos = screenWidth + Math.cos(angle * Math.PI / 180D) * radius - 13.6;
						double yPos = screenHeight + Math.sin(angle * Math.PI / 180D) * radius - 13.6;
						if (mx > xPos && mx < xPos + 27.2 && my > yPos && my < yPos + 27.2)
						{
							activeSector = curItem;
							GlStateManager.pushMatrix();
							GlStateManager.translate(xPos, yPos, 0);
							GlStateManager.scale(1.7, 1.7, 1.7);
							if (getSpellStackFromID(spellArray[curItem]) != null)
							{
								mc.getRenderItem().renderItemIntoGUI(getSpellStackFromID(spellArray[curItem]), 0, 0);
							}
							GlStateManager.translate(-xPos, -yPos, 0);
							GlStateManager.popMatrix();
						}
						else
						{
							xPos = screenWidth + Math.cos(angle * Math.PI / 180D) * radius - 12;
							yPos = screenHeight + Math.sin(angle * Math.PI / 180D) * radius - 12;
							GlStateManager.pushMatrix();
							GlStateManager.translate(xPos, yPos, 0);
							GlStateManager.scale(1.5, 1.5, 1.5);
							if (getSpellStackFromID(spellArray[curItem]) != null)
							{
								mc.getRenderItem().renderItemIntoGUI(getSpellStackFromID(spellArray[curItem]), 0, 0);
							}
							GlStateManager.translate(-xPos, -yPos, 0);
							GlStateManager.popMatrix();
						}
						angle += anglePer;
					}
				}
				net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
			}
		}
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException 
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		if (activeSector != -1)
		{
			NBTLib.setInt(Minecraft.getMinecraft().thePlayer.getHeldItemOffhand(), "selectedSpell", spellArray[activeSector]);
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if(!isKeyDown(KeyBindings.pickSpell))
		{
			mc.displayGuiScreen(null);
		
			// idk what this code is doing lol
			if(slotSelected != -1) 
			{
				PacketManager.INSTANCE.sendToServer(new PacketSendKey());
			}
		}
		
		ImmutableSet<KeyBinding> set = ImmutableSet.of(mc.gameSettings.keyBindForward, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindSneak, mc.gameSettings.keyBindSprint, mc.gameSettings.keyBindJump);
		for(KeyBinding k : set)
			KeyBinding.setKeyBindState(k.getKeyCode(), isKeyDown(k));

		timeIn++;
	}

	public boolean isKeyDown(KeyBinding keybind) {
		int key = keybind.getKeyCode();
		if(key < 0) {
			int button = 100 + key;
			return Mouse.isButtonDown(button);
		}
		return Keyboard.isKeyDown(key);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	private static float mouseAngle(int x, int y, int mx, int my) {
		Vector2f baseVec = new Vector2f(1F, 0F);
		Vector2f mouseVec = new Vector2f(mx - x, my - y);

		float ang = (float) (Math.acos(Vector2f.dot(baseVec, mouseVec) / (baseVec.length() * mouseVec.length())) * (180F / Math.PI));
		return my < y ? 360F - ang : ang;
	}

}
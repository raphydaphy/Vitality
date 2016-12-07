package com.raphydaphy.vitality.init;

import com.raphydaphy.vitality.Vitality;
import com.raphydaphy.vitality.item.ItemBase;
import com.raphydaphy.vitality.item.ItemSpellBag;
import com.raphydaphy.vitality.item.ItemWand;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{

	public static ItemBase ingot_copper;
	public static ItemBase ingot_tin;
	public static ItemBase ingot_bronze;
	
	public static ItemBase ingot_bronze_imbued;
	public static ItemBase imbued_stick;
	
	public static ItemBase ingot_fluxed;
	public static ItemBase fluxed_stick;
	
	public static ItemSpellBag spell_bag;

	public static ItemBase spell_card_vital_extraction;
	public static ItemBase spell_card_lantern;
	public static ItemBase spell_card_explosion;
	public static ItemBase spell_card_fireball;
	public static ItemBase spell_card_rapidfire;
	public static ItemBase spell_card_transmutation;
	public static ItemBase spell_card_hunger;
	public static ItemBase spell_card_enhanced_extraction;
	public static ItemBase spell_card_flight;
	public static ItemBase spell_card_forcefield;

	public static ItemBase upgrade_template_basic;

	public static ItemWand basic_wand_copper;
	public static ItemWand basic_wand_tin;
	public static ItemWand advanced_wand;
	public static ItemWand master_wand;

	public static ItemWand wand;

	public static void init()
	{
		ingot_copper = register(new ItemBase("ingot_copper", 64).setCreativeTab(Vitality.creativeTab));
		ingot_tin = register(new ItemBase("ingot_tin", 64).setCreativeTab(Vitality.creativeTab));
		ingot_bronze = register(new ItemBase("ingot_bronze", 64).setCreativeTab(Vitality.creativeTab));
		ingot_bronze_imbued = register(
				new ItemBase("ingot_bronze_imbued", 64).setCreativeTab(Vitality.creativeTab));
		ingot_fluxed = register(
				new ItemBase("ingot_fluxed", 64).setCreativeTab(Vitality.creativeTab));
		fluxed_stick = register(new ItemBase("fluxed_stick", 64).setCreativeTab(Vitality.creativeTab));
		imbued_stick = register(new ItemBase("imbued_stick", 64).setCreativeTab(Vitality.creativeTab));
		spell_bag = register(
				new ItemSpellBag().setCreativeTab(Vitality.creativeTab));
		spell_card_lantern = register(
				new ItemBase("spell_card_lantern", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_explosion = register(
				new ItemBase("spell_card_explosion", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_fireball = register(
				new ItemBase("spell_card_fireball", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_rapidfire = register(
				new ItemBase("spell_card_rapidfire", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_hunger = register(
				new ItemBase("spell_card_hunger", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_transmutation = register(
				new ItemBase("spell_card_transmutation", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_vital_extraction = register(
				new ItemBase("spell_card_vital_extraction", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_enhanced_extraction = register(
				new ItemBase("spell_card_enhanced_extraction", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_flight = register(
				new ItemBase("spell_card_flight", 1).setCreativeTab(Vitality.creativeTab));
		spell_card_forcefield = register(
				new ItemBase("spell_card_forcefield", 1).setCreativeTab(Vitality.creativeTab));
		upgrade_template_basic = register(
				new ItemBase("upgrade_template_basic", 16).setCreativeTab(Vitality.creativeTab));
		basic_wand_copper = register(new ItemWand("basic_wand_copper", 1, ConfigHandler.wandSettings.basicWandStorage,
				ConfigHandler.wandSettings.canBasicWandBreak).setCreativeTab(Vitality.creativeTab));
		basic_wand_tin = register(new ItemWand("basic_wand_tin", 1, ConfigHandler.wandSettings.basicWandStorage,
				ConfigHandler.wandSettings.canBasicWandBreak).setCreativeTab(Vitality.creativeTab));
		advanced_wand = (ItemWand) register(
				new ItemWand("advanced_wand", 2, ConfigHandler.wandSettings.advancedWandStorage, false)
						.setCreativeTab(Vitality.creativeTab));
		master_wand = (ItemWand) register(
				new ItemWand("master_wand", 3, ConfigHandler.wandSettings.masterWandStorage, false)
						.setCreativeTab(Vitality.creativeTab));

	}

	private static <T extends Item> T register(T item)
	{
		GameRegistry.register(item);
		if (item instanceof ItemBase)
		{
			((ItemBase) item).registerItemModel();
		}
		else if (item instanceof ItemWand)
		{
			((ItemWand) item).registerItemModel();
		}
		else if (item instanceof ItemSpellBag)
		{
			((ItemSpellBag) item).registerItemModel();
		}
		return item;
	}

}
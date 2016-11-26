package com.raphydaphy.enhancedprogression.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipies
{
	private static void addOreDictRecipe(ItemStack output, Object... recipe)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
	}

	public static void registerCrafting()
	{
		OreDictionary.registerOre("dustCoal", Items.COAL);
		OreDictionary.registerOre("dustCoal", new ItemStack(Items.COAL, 1, 1));
		OreDictionary.registerOre("powderBlaze", Items.BLAZE_POWDER);

		OreDictionary.registerOre("ingotCopper", ModItems.ingot_copper);
		OreDictionary.registerOre("ingotTin", ModItems.ingot_tin);
		OreDictionary.registerOre("ingotBronze", ModItems.ingot_copper);

		addOreDictRecipe(new ItemStack(ModItems.basic_wand_copper), "  C", " S ", "S  ", 'S', "stickWood", 'C',
				"ingotCopper");
		addOreDictRecipe(new ItemStack(ModItems.basic_wand_tin), "  C", " S ", "S  ", 'S', "stickWood", 'C',
				"ingotTin");

		addOreDictRecipe(new ItemStack(ModItems.spell_card_lantern), "SCS", "CBC", "SCS", 'S', "stickWood", 'C',
				"dustCoal", 'B', "ingotBronze");
		addOreDictRecipe(new ItemStack(ModItems.spell_card_vital_extraction), "SLS", "LBL", "SLS", 'S', "stickWood",
				'L', "logWood", 'B', "ingotBronze");
		addOreDictRecipe(new ItemStack(ModItems.spell_card_explosion), "SGS", "GBG", "SGS", 'S', "stickWood", 'G',
				"gunpowder", 'B', "ingotBronze");
		addOreDictRecipe(new ItemStack(ModItems.spell_card_transmutation), "SPS", "PBP", "SPS", 'S', "stickWood", 'P',
				"powderBlaze", 'B', "ingotBronze");

		GameRegistry.addShapedRecipe(new ItemStack(ModItems.advanced_wand), "  B", " S ", "S  ", 'S',
				ModItems.imbued_stick, 'B', ModItems.ingot_bronze_imbued);

		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingot_bronze, 4), ModItems.ingot_tin,
				ModItems.ingot_copper, ModItems.ingot_copper, ModItems.ingot_copper);
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.imbued_plank, 4), ModBlocks.imbued_log);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.imbued_stick, 4), "P", "P", 'P', ModBlocks.imbued_plank);

		GameRegistry.addShapedRecipe(new ItemStack(ModItems.spell_card_rapidfire), "SAS", "ABA", "SAS", 'S',
				ModItems.imbued_stick, 'A', Items.ARROW, 'B', ModItems.ingot_bronze_imbued);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.spell_card_hunger), "SRS", "RBR", "SRS", 'S',
				ModItems.imbued_stick, 'R', Items.ROTTEN_FLESH, 'B', ModItems.ingot_bronze_imbued);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.spell_card_enhanced_extraction), "SDS", "DBD", "SDS", 'S',
				ModItems.imbued_stick, 'D', Items.DIAMOND, 'B', ModItems.ingot_bronze_imbued);
	}

	public static void registerSmelting()
	{
		GameRegistry.addSmelting(ModBlocks.ore_copper, new ItemStack(ModItems.ingot_copper), 0.7f);
		GameRegistry.addSmelting(ModBlocks.ore_tin, new ItemStack(ModItems.ingot_tin), 0.7f);
	}
}

package com.raphydaphy.vitality.recipe;

import javax.annotation.Nullable;

import com.raphydaphy.vitality.api.essence.Essence;
import com.raphydaphy.vitality.api.spell.Spell;
import com.raphydaphy.vitality.api.wand.WandEnums;
import com.raphydaphy.vitality.api.wand.WandHelper;
import com.raphydaphy.vitality.item.ItemWandPiece;
import com.raphydaphy.vitality.registry.ModItems;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipeWand implements IRecipe {
	private ItemStack resultItem;

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	public boolean matches(InventoryCrafting inv, World worldIn) {
		this.resultItem = null;

		int cores = 0;
		int tips = 0;
		WandEnums.CoreType coreType = null;
		WandEnums.TipType tipType1 = null;
		WandEnums.TipType tipType2 = null;

		for (int k1 = 0; k1 < inv.getSizeInventory(); ++k1) {
			ItemStack itemstack = inv.getStackInSlot(k1);

			if (itemstack != null) {
				if (!(itemstack.getItem() instanceof ItemWandPiece))
				{
					return false;
				}
				if (itemstack.getItem() == ModItems.TIP_WOODEN) {
					if (!(k1 == 2 || k1 == 6)) { return false; }
					tips++;
					if (tips == 1) {
						tipType1 = WandEnums.TipType.WOODEN;
					} else if (tips == 2) {
						tipType2 = WandEnums.TipType.WOODEN;
					}
				}

				if (itemstack.getItem() == ModItems.CORE_ANGELIC) {
					if (k1 != 4) { return false; }
					cores++;
					coreType = WandEnums.CoreType.ANGELIC;
				} else if (itemstack.getItem() == ModItems.CORE_ATMOSPHERIC) {
					if (k1 != 4) { return false; }
					cores++;
					coreType = WandEnums.CoreType.ATMOSPHERIC;
				} else if (itemstack.getItem() == ModItems.CORE_DEMONIC) {
					if (k1 != 4) { return false; }
					cores++;
					coreType = WandEnums.CoreType.DEMONIC;
				} else if (itemstack.getItem() == ModItems.CORE_ENERGETIC) {
					if (k1 != 4) { return false; }
					cores++;
					coreType = WandEnums.CoreType.ENERGETIC;
				} else if (itemstack.getItem() == ModItems.CORE_EXOTIC) {
					if (k1 != 4) { return false; }
					cores++;
					coreType = WandEnums.CoreType.EXOTIC;
				}
			}
		}

		if (tips > 2 || tips < 2) {
			return false;
		} else if (cores > 1 || cores < 1) {
			return false;
		} else if (tipType1 != tipType2) {
			return false;
		}
		
		// aandddd this is why we need a new oven
		switch(tipType1)
		{
		case WOODEN:
		switch (coreType)
		{
		case ANGELIC:
			resultItem = new ItemStack(ModItems.WAND_ANGELIC);
			break;
		case ATMOSPHERIC:
			resultItem = new ItemStack(ModItems.WAND_ATMOSPHERIC);
			break;
		case DEMONIC:
			resultItem = new ItemStack(ModItems.WAND_DEMONIC);
			break;
		case ENERGETIC:
			resultItem = new ItemStack(ModItems.WAND_ENERGETIC);
			break;
		case EXOTIC:
			resultItem = new ItemStack(ModItems.WAND_EXOTIC);
			break;
		}
		}
		resultItem.setTagCompound(new NBTTagCompound());
		resultItem.getTagCompound().setString(WandHelper.CORE_TYPE, coreType.getName());
		resultItem.getTagCompound().setString(WandHelper.TIP_TYPE, tipType1.getName());
		resultItem.getTagCompound().setInteger(Essence.KEY, 0);
		resultItem.getTagCompound().setInteger(Spell.ACTIVE_KEY, -1);

		int maxEssence = coreType.getTier().getMaxCap();
		resultItem.getTagCompound().setInteger(Essence.MAX_KEY, maxEssence);
		return true;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Nullable
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return this.resultItem.copy();
	}

	/**
	 * Returns the size of the recipe area
	 */
	public int getRecipeSize() {
		return 10;
	}

	@Nullable
	public ItemStack getRecipeOutput() {
		return this.resultItem;
	}

	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		ItemStack[] aitemstack = new ItemStack[inv.getSizeInventory()];

		for (int i = 0; i < aitemstack.length; ++i) {
			ItemStack itemstack = inv.getStackInSlot(i);
			aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
		}

		return aitemstack;
	}
}

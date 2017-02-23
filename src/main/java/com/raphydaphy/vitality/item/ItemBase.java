package com.raphydaphy.vitality.item;

import com.raphydaphy.vitality.Vitality;
import com.raphydaphy.vitality.init.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item
{

	protected static String name;

	public ItemBase(String name, int maxStack)
	{
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setCreativeTab(Reference.creativeTab);
		this.maxStackSize = maxStack;
	}

	public void registerItemModel()
	{
		Vitality.proxy.registerItemRenderer(this, 0, name);
	}

	@Override
	public ItemBase setCreativeTab(CreativeTabs tab)
	{
		super.setCreativeTab(tab);
		return this;
	}
}
package com.raphydaphy.vitality.api.spell;

import com.raphydaphy.vitality.registry.ModItems;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;

public enum Spell implements IStringSerializable{
	ILLUMINATION("Illumination",0,ModItems.SPELL_ILLUMINATION),
	FIREBALL("Fireball",1,ModItems.SPELL_FIREBALL),
	;

	private String name;
	private int id;
	private Item item;
	
	/**
	 * Points to the currently selected spell for any wand ItemStack
	 */
	public static final String ACTIVE_KEY = "active_spell"; 
	
	/**
	 * This is the key that is used in spell bags to store the array
	 * of spells stored within the bag.
	 */
	public static final String ARRAY_KEY = "stored_spells";
	
	Spell(String name,int id, Item spellItem){
		this.name = name;
		this.id = id;
		this.item = spellItem;
		
		// prints true
		System.out.println(spellItem == null);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public Item getAsItem() {
		if (item != null)
		{
			return item;
		}
		return ModItems.SPELL_FIREBALL;
	}

}

package com.raphydaphy.vitality.util.shadows.registry;

import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

/**
 * 
 * @author Shadows You can implement this class in a metadata using item or
 *         block. DO NOT do it in both a Block and it's ItemBlock!
 */
public interface IMeta {

	/**
	 * 
	 * @return An integer representing the number of sub items on this block or
	 *         item id.
	 */
	@Nonnull
	public int getMaxMeta();

	/**
	 * 
	 * @return A list of model resource locations for each meta. They MUST be in
	 *         order.
	 */
	@Nonnull
	public List<ModelResourceLocation> getMetaModelLocations();

}

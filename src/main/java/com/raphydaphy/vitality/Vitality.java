package com.raphydaphy.vitality;

import com.raphydaphy.vitality.init.Events;
import com.raphydaphy.vitality.init.ModRecipies;
import com.raphydaphy.vitality.init.Reference;
import com.raphydaphy.vitality.init.VitalityCreativeTab;
import com.raphydaphy.vitality.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MINECRAFT_VERSION)
public class Vitality
{
	@Instance
	public static Vitality Instance;

	public static final VitalityCreativeTab creativeTab = new VitalityCreativeTab();

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		System.out.println("Setting up vital prerequisites");
		MinecraftForge.EVENT_BUS.register(new Events());
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		System.out.println("Initializing Vitality...");
		ModRecipies.registerCrafting();
		ModRecipies.registerSmelting();
		
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		System.out.println("Vitality Loaded Successfully :D");
	}

}
package crego.modjam3.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import crego.modjam3.client.gui.GuiHandler;
import crego.modjam3.common.blocks.BlockHandler;
import crego.modjam3.common.info.IDInfo;
import crego.modjam3.common.info.ModInfo;
import crego.modjam3.common.item.ItemHandler;
import crego.modjam3.common.network.PacketHandler;
import crego.modjam3.common.proxy.CommonProxy;

@Mod(modid = ModInfo.MODID, name = ModInfo.MODNAME, version = ModInfo.VERSION)
@NetworkMod(channels = { ModInfo.CHANAL },packetHandler = PacketHandler.class, clientSideRequired = true, serverSideRequired = true)
public class CraftedTools {

	// Instance
	@Instance(ModInfo.MODID)
	public static CraftedTools instance;

	// Proxy

	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.COMMON_PROXY)
	public static CommonProxy proxy;

	// Pre-Init

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		ItemHandler.init();
		BlockHandler.init();
		proxy.initRender();
	}

	// Init

	@EventHandler
	public void preInit(FMLInitializationEvent e) {
		new GuiHandler();
		GameRegistry.addSmelting(Block.anvil.blockID, new ItemStack(IDInfo.CTAnvilID ,1,1),10F);
	}

	// Post-Init

	@EventHandler
	public void preInit(FMLPostInitializationEvent e) {

	}

}

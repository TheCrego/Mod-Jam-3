package crego.modjam3.common.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import crego.modjam3.client.render.RenderItemCT;
import crego.modjam3.client.render.RenderItemCTAnvil;
import crego.modjam3.client.render.RenderTileCTAnvil;
import crego.modjam3.common.blocks.TileCTAnvil;
import crego.modjam3.common.info.IDInfo;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy{
	
	public void initRender() {
		MinecraftForgeClient.registerItemRenderer(IDInfo.testItemID+256, new RenderItemCT());
		MinecraftForgeClient.registerItemRenderer(IDInfo.CTAnvilID, new RenderItemCTAnvil());
		ClientRegistry.bindTileEntitySpecialRenderer(TileCTAnvil.class, new RenderTileCTAnvil());
	}
}

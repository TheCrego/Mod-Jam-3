package crego.modjam3.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import crego.modjam3.common.CraftedTools;
import crego.modjam3.common.blocks.TileCTAnvil;
import crego.modjam3.common.container.ContainerCTAnvil;

public class GuiHandler implements IGuiHandler {
	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(CraftedTools.instance,
				this);
	}

	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case 0:
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (te != null && te instanceof TileCTAnvil) {
				return new ContainerCTAnvil(player.inventory, te);
			}
			break;
	}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch (ID) {
		case 0:
			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (te != null && te instanceof TileCTAnvil) {
				return new GuiAnvil(player.inventory, te);
			}

			break;
	}
		return null;
	}

}

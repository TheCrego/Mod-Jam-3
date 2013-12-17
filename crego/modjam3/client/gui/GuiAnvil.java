package crego.modjam3.client.gui;

import org.lwjgl.opengl.GL11;

import crego.modjam3.client.model.ModelCT;
import crego.modjam3.common.CTToolMaterial;
import crego.modjam3.common.container.ContainerCTAnvil;
import crego.modjam3.common.info.IDInfo;
import crego.modjam3.common.info.ModInfo;
import crego.modjam3.common.network.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiAnvil extends GuiContainer {

	private boolean previv = false;
	private int r = 0;
	private int layer = 7;

	private ResourceLocation texture = new ResourceLocation(
			"crafted_tools:textures/guianvil.png");
	private ResourceLocation texture2 = new ResourceLocation(
			"crafted_tools:textures/guianvil2.png");
	private ResourceLocation bar = new ResourceLocation(
			"crafted_tools:textures/bar.png");
	private ResourceLocation pix = new ResourceLocation(
			"crafted_tools:textures/pixel.png");
	private TileEntity t;

	public GuiAnvil(InventoryPlayer invPlayer, TileEntity tile) {
		super(new ContainerCTAnvil(invPlayer, tile));

		t = tile;
		this.xSize = 194;
		this.ySize = 256;
	}

	public void initGui() {
		super.initGui();
		buttonList.clear();
		buttonList.add(new GuiButton(0, guiLeft + 26, guiTop + 5, 29, 20,
				"Vieve"));
		buttonList.add(new GuiButton(1, guiLeft + 24, guiTop + 158, 20, 20,
				"Up"));
		buttonList.add(new GuiButton(2, guiLeft + 46, guiTop + 158, 30, 20,
				"Down"));
		buttonList.add(new GuiButton(3, guiLeft + 142, guiTop + 27,40, 20,
				"Dump"));

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(previv ? texture
				: texture2);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		Minecraft.getMinecraft().renderEngine.bindTexture(bar);
		if (((IInventory) t).getStackInSlot(0) != null)
			if (((IInventory) t).getStackInSlot(0).stackTagCompound != null)
				drawTexturedModalRect(guiLeft+57, guiTop+8, 0, 0, (int) (5.6875F*((IInventory) t).getStackInSlot(0).stackTagCompound.getByte("x42y42z42")), 16);
		if (previv) {
			if (((IInventory) t).getStackInSlot(0) != null) {
				r++;
				GL11.glPushMatrix();
				GL11.glTranslatef(this.guiLeft + 90, this.guiTop + 100, 100);
				GL11.glRotated(r, 0, 1, 0);
				GL11.glScaled(30, 30, 30);
				RenderManager.instance.itemRenderer.renderItem(null,
						((IInventory) t).getStackInSlot(0), 0);
				GL11.glPopMatrix();
			}
		} else {
			drawString(fontRenderer, "Layer: " + layer, guiLeft + 80,
					guiTop + 164, 0x666666);

			for (int a = 0; a < 16; a++) {
				for (int b = 0; b < 16; b++) {
					if (((IInventory) t).getStackInSlot(0) != null)
						if (((IInventory) t).getStackInSlot(0).stackTagCompound != null)
							if (((IInventory) t).getStackInSlot(0).stackTagCompound
									.getByte("x" + (a - 8) + "y" + layer + "z"
											+ (b - 8)) != 0){
								Minecraft.getMinecraft().renderEngine
										.bindTexture(CTToolMaterial.all[((IInventory) t)
												.getStackInSlot(0).stackTagCompound
												.getByte("x" + (a - 8) + "y"
														+ layer + "z" + (b - 8))].guiColor);
								drawTexturedModalRect(guiLeft + 8 + a * 8,
										guiTop + 28 + b * 8, 0, 0, 7, 7);
							}
					
				}
			}
		}

	}

	protected void actionPerformed(GuiButton button) {
		if (button.id == 0) {
			previv = !previv;
			button.displayString = previv ? "Edit" : "Vieve";
			((GuiButton) this.buttonList.get(1)).yPosition = previv ? -100
					: guiTop + 158;
			((GuiButton) this.buttonList.get(2)).yPosition = previv ? -100
					: guiTop + 158;
		}
		if (button.id == 1) {
			if (layer < 7)
				layer++;
		}
		if (button.id == 2) {
			if (layer > -8)
				layer--;
		}
		if (button.id == 3) {
			PacketHandler.setPixel(t.xCoord, t.yCoord, t.zCoord, 42, 42, 42, (byte) 0);
		}
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		for (int a = 0; a < 16; a++) {
			for (int b = 0; b < 16; b++) {
				if (par1 > 8 + a * 8 + guiLeft && par1 < 16 + a * 8 + guiLeft
						&& par2 > 28 + b * 8 + guiTop
						&& par2 < 36 + b * 8 + guiTop)
					if(((IInventory) t).getStackInSlot(0) != null)
						if (((IInventory) t).getStackInSlot(0).stackTagCompound != null){
							if(((IInventory) t).getStackInSlot(0).stackTagCompound.getByte("x42y42z42")>0)
							PacketHandler.setPixel(t.xCoord, t.yCoord, t.zCoord, a - 8,
									
							layer, b - 8, par3 == 0 ? (byte) ((IInventory) t).getStackInSlot(0).stackTagCompound.getByte("type") : 0);
							if(par3 == 0)
								PacketHandler.setPixel(t.xCoord, t.yCoord, t.zCoord, 42, 42, 42, (byte) (((IInventory) t).getStackInSlot(0).stackTagCompound.getByte("x42y42z42")-1));
						}
							
							
			}
		}

	}
}

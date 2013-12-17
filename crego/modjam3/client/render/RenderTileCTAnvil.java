package crego.modjam3.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

public class RenderTileCTAnvil extends TileEntitySpecialRenderer {

	private IModelCustom model;

	public RenderTileCTAnvil() {
		this.model = AdvancedModelLoader.loadModel("/assets/crafted_tools/models/anvil.obj");
	}

	public static final ResourceLocation texture = new ResourceLocation(
			"crafted_tools:textures/anvil.png");

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTickTime) {
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float)x + 0.5F, (float)y+0.4F , (float)z + 0.5F);
		
		GL11.glRotatef(180, 1, 0, 0);
		
		if(tileentity.worldObj.getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord)==1)
			GL11.glRotatef(90, 0, 1, 0);
		GL11.glScalef(0.4F, 0.4F, 0.3F);
			
		bindTexture(texture);
		model.renderAll();
		GL11.glPopMatrix();
	}

}
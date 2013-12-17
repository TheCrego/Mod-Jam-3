package crego.modjam3.client.render;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderItemCTAnvil implements IItemRenderer{

	private IModelCustom model;	
	public RenderItemCTAnvil(){
		this.model = AdvancedModelLoader.loadModel("/assets/crafted_tools/models/anvil.obj");
	}
	public static final ResourceLocation texture = new ResourceLocation(
			"crafted_tools:textures/anvil.png");
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
GL11.glPushMatrix();
		
		GL11.glRotatef(180, 1, 0, 0);

		switch(type){
			case INVENTORY:
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				break;
			case EQUIPPED:
				GL11.glScalef(0.25F, 0.25F, 0.25F);
				GL11.glTranslatef(3, -2, -2);
			case EQUIPPED_FIRST_PERSON:
				GL11.glScalef(0.5F, 0.5F, 0.5F);
				GL11.glTranslatef(3, 0, 0);
				
				
		}
			
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
		GL11.glPopMatrix();
	}

}

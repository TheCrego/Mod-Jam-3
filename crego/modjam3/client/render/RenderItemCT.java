package crego.modjam3.client.render;

import org.lwjgl.opengl.GL11;

import crego.modjam3.client.model.ModelCT;
import crego.modjam3.common.info.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.IModelCustom;

public class RenderItemCT implements IItemRenderer{
	
	NBTTagCompound c = new NBTTagCompound();
	ModelCT model1,model2,model3,model4,model5,model6;
	ResourceLocation r1,r2,r3,r4,r5,r6;
	
	public RenderItemCT(){
		r1 = new ResourceLocation("minecraft:textures/blocks/planks_oak.png");
		r2 = new ResourceLocation("minecraft:textures/blocks/stone.png");
		r3 = new ResourceLocation("minecraft:textures/blocks/iron_block.png");
		r4 = new ResourceLocation("minecraft:textures/blocks/gold_block.png");
		r5 = new ResourceLocation("minecraft:textures/blocks/diamond_block.png");
		r6 = new ResourceLocation("minecraft:textures/blocks/obsidian.png");
	}
	private void updatemodel(ItemStack item){
		model1 = new ModelCT(item.stackTagCompound, (byte) 1);
		model2 = new ModelCT(item.stackTagCompound, (byte) 2);
		model3 = new ModelCT(item.stackTagCompound, (byte) 3);
		model4 = new ModelCT(item.stackTagCompound, (byte) 4);
		model5 = new ModelCT(item.stackTagCompound, (byte) 5);
		model6 = new ModelCT(item.stackTagCompound, (byte) 6);
	}
	
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
		if(model1 == null || c != item.stackTagCompound){
			c = item.stackTagCompound;
			updatemodel(item);
		}
		switch (type) {
		case INVENTORY:
			GL11.glRotatef(-90, 0,1,0);
			break;
		case EQUIPPED:
			GL11.glTranslatef(0.5F, 0.7F, 0.6F);
			GL11.glRotated(-45, 0, 1, 0);
			GL11.glRotated(70, 0, 0, 1);
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glRotated(60, 0, 1, 0);
			GL11.glScalef(0.7F, 0.7F, 0.7F);
			GL11.glTranslatef(-1F, 1.5F, 0);
			break;
		default:
	}
		Minecraft.getMinecraft().renderEngine.bindTexture(r1);
		model1.render(null, 0, 0, 0, 0, 0, 0.1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(r2);
		model2.render(null, 0, 0, 0, 0, 0, 0.1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(r3);
		model3.render(null, 0, 0, 0, 0, 0, 0.1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(r4);
		model4.render(null, 0, 0, 0, 0, 0, 0.1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(r5);
		model5.render(null, 0, 0, 0, 0, 0, 0.1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(r6);
		model6.render(null, 0, 0, 0, 0, 0, 0.1F);
	}

}

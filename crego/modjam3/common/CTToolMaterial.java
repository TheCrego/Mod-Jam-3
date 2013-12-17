package crego.modjam3.common;

import java.util.Random;

import crego.modjam3.common.info.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CTToolMaterial {
	
	protected static Random random = new Random();
	
	public byte id;
	public int from;
	public int str;
	private int dur;
	public ResourceLocation guiColor;
	public ResourceLocation render;
	public static CTToolMaterial[] all = new CTToolMaterial[Byte.MAX_VALUE];
	
	public static CTToolMaterial wood = new CTToolMaterial((byte) 1,Block.wood.blockID, 0, 50, new ResourceLocation(ModInfo.MODID+":textures/t0.png"), new ResourceLocation(ModInfo.MODID+":wood.png"));
	public static CTToolMaterial stone = new CTToolMaterial((byte) 2,Block.stone.blockID, 1, 4, new ResourceLocation(ModInfo.MODID+":textures/t1.png"), new ResourceLocation(ModInfo.MODID+":stone.png"));
	public static CTToolMaterial iron = new CTToolMaterial((byte) 3,Item.ingotIron.itemID, 3, 10, new ResourceLocation(ModInfo.MODID+":textures/t2.png"), new ResourceLocation(ModInfo.MODID+":iron.png"));
	public static CTToolMaterial gold = new CTToolMaterial((byte) 4,Item.ingotGold.itemID, 5, 3, new ResourceLocation(ModInfo.MODID+":textures/t3.png"), new ResourceLocation(ModInfo.MODID+":iron.png"));
	public static CTToolMaterial diamond = new CTToolMaterial((byte) 5,Item.diamond.itemID, 6, 25, new ResourceLocation(ModInfo.MODID+":textures/t4.png"), new ResourceLocation(ModInfo.MODID+":iron.png"));
	
	public static CTToolMaterial obsidian = new CTToolMaterial((byte) 6,Block.obsidian.blockID, 0, 100, new ResourceLocation(ModInfo.MODID+":textures/t5.png"), new ResourceLocation(ModInfo.MODID+":obsidian.png"));
	
	
	public CTToolMaterial(byte id, int from, int str, int dur, ResourceLocation guiColor, ResourceLocation render){
		this.id = id;
		this.from = from;
		this.str = str;
		this.dur = dur;
		this.guiColor = guiColor;
		all[id] = this;
	}
}

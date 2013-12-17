package crego.modjam3.common.blocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.jcraft.jorbis.Block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import crego.modjam3.common.info.IDInfo;

public class BlockHandler {
	
	public static BlockCTAnvil anvil;
	
	public static void init(){
		anvil = (BlockCTAnvil) new BlockCTAnvil(IDInfo.CTAnvilID).setUnlocalizedName("CT_Anvil").setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(anvil,"CT_Anvil");
		GameRegistry.registerTileEntity(TileCTAnvil.class, "tileacanvil");
		
		LanguageRegistry.addName(new ItemStack(anvil,1), "Crafted Tools Anvil");
	}
}

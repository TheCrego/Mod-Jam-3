package crego.modjam3.common.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import crego.modjam3.common.info.IDInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemHandler {
	
	public static Item testItem;
	
	
	public static void init(){
		testItem = new ItemCT(IDInfo.testItemID).setCreativeTab(CreativeTabs.tabBlock).setUnlocalizedName("testItem");
		LanguageRegistry.addName(new ItemStack(testItem), "Crafted Tool");
	}

}

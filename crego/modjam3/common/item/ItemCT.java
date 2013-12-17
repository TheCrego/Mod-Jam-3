package crego.modjam3.common.item;

import crego.modjam3.common.CTToolMaterial;
import crego.modjam3.common.info.IDInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCT extends Item{

	public ItemCT(int par1) {
		super(par1);
		this.setMaxStackSize(1);
	}
	
	public boolean getShareTag()
    {
        return true;
    }
	
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par1ItemStack.stackTagCompound = new NBTTagCompound();
		for(int x = -8;x<8;x++)
			for(int y = -8;y<8;y++)
				for(int z = -8;z<8;z++)
					par1ItemStack.stackTagCompound.setByte("x"+x+"y"+y+"z"+z, (byte) 0);
	}
	 public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		int dmg = 0;
		for(int x = -8;x<8;x++)
			for(int y = -8;y<8;y++)
				for(int z = -8;z<8;z++){
					
					CTToolMaterial mt  = CTToolMaterial.all[stack.stackTagCompound.getByte("x"+x+"y"+y+"z"+z)];
					if(mt != null)
						dmg += mt.str;
				}
		dmg = dmg/IDInfo.divid;
		((EntityLivingBase) entity).setHealth(((EntityLivingBase) entity).getHealth()-dmg);
        return false;
    }

}

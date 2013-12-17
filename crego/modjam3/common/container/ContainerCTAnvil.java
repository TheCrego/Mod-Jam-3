package crego.modjam3.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerCTAnvil extends Container{

	private TileEntity tile;
	
	public ContainerCTAnvil(InventoryPlayer invPlayer,TileEntity tile){
		this.tile = tile;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9+9, 8 + 18 * x, 182 + y * 18));
			}
		}
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x , 8 + 18 * x, 236));
		}
		addSlotToContainer(new Slot((IInventory)tile,0,8,8));
		addSlotToContainer(new Slot((IInventory)tile,1,152,8));
		addSlotToContainer(new Slot((IInventory)tile,2,170,8));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return ((IInventory) tile).isUseableByPlayer(entityplayer);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}

}

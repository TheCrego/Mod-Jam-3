package crego.modjam3.common.blocks;

import crego.modjam3.common.CTToolMaterial;
import crego.modjam3.common.info.IDInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileCTAnvil extends TileEntity implements IInventory{

	private ItemStack[] items;
		
		public TileCTAnvil() {
			items = new ItemStack[3];	
		}

		@Override
		public int getSizeInventory() {
			return items.length;
		}

		@Override
		public ItemStack getStackInSlot(int i) {
			return items[i];
		}

		@Override
		public ItemStack decrStackSize(int i, int j) {
			ItemStack s = getStackInSlot(i);
			if (s != null) {
				if (s.stackSize <= j) {
					setInventorySlotContents(i, null);
				}else{
					s = s.splitStack(j);
					onInventoryChanged();
				}
			}
			return s;
		}

		@Override
		public ItemStack getStackInSlotOnClosing(int i) {
			ItemStack item = getStackInSlot(i);
			setInventorySlotContents(i, null);
			return item;
		}

		@Override
		public void setInventorySlotContents(int i, ItemStack s) {
			items[i] = s;		
			if (s != null && s.stackSize > getInventoryStackLimit()) {
				s.stackSize = getInventoryStackLimit();
			}
			onInventoryChanged();
		}

		@Override
		public String getInvName() {
			return "CTAnvil";
		}

		@Override
		public boolean isInvNameLocalized() {
			return false;
		}
		

		@Override
		public int getInventoryStackLimit() {
			return 1;
		}

		@Override
		public boolean isUseableByPlayer(EntityPlayer entityplayer) {
			return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
		}

		@Override
		public void openChest() {
			//UNUSED
			
		}
		public void onInventoryChanged() {
			super.onInventoryChanged();
			if(items[2] == null){
				items[2] = new ItemStack(IDInfo.testItemID+256,1,0);
				items[2].getItem().onCreated(items[2], worldObj, null);
			}
				
			if(items[0] != null && items[1] != null){
				if(items[0].stackTagCompound == null)
					items[0].stackTagCompound = new NBTTagCompound();
				if(items[0].stackTagCompound.getByte("type") == 0||items[0].stackTagCompound.getByte("x42y42z42")<1){
					for(CTToolMaterial material:CTToolMaterial.all){
						if(material != null){
							if(material.from == items[1].itemID){
								items[0].stackTagCompound.setByte("type", material.id);
								items[0].stackTagCompound.setByte("x42y42z42", (byte) 16);
								decrStackSize(1, 1);
								return;
							}				
						}
					}
				}
			}
		}

		@Override
		public void closeChest() {
			//UNUSED
			
		}

		@Override
		public boolean isItemValidForSlot(int i, ItemStack itemstack) {
			return true;
		}
		@Override
		public void writeToNBT(NBTTagCompound compound) {
			super.writeToNBT(compound);
			
			NBTTagList items = new NBTTagList();
			
			for (int i = 0; i < getSizeInventory(); i++) {		
				ItemStack stack = getStackInSlot(i);
				
				if (stack != null) {
					NBTTagCompound item = new NBTTagCompound();
					item.setByte("s", (byte)i);
					stack.writeToNBT(item);
					items.appendTag(item);
				}
			}
			
			compound.setTag("i", items);
		}
		
		@Override
		public void readFromNBT(NBTTagCompound compound) {
			super.readFromNBT(compound);
			
			NBTTagList items = compound.getTagList("i");
			
			for (int i = 0; i < items.tagCount(); i++) {
				NBTTagCompound item = (NBTTagCompound)items.tagAt(i);
				int slot = item.getByte("s");
				
				if (slot >= 0 && slot < getSizeInventory()) {
					setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
				}
			}
		}

}

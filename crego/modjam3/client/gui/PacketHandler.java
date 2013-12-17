package crego.modjam3.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.inventory.IInventory;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput reader = ByteStreams.newDataInput(packet.data);
		int x = reader.readInt();
		int y = reader.readInt();
		int z = reader.readInt();
		EntityPlayer p = (EntityPlayer) player;
		((IInventory)p.worldObj.getBlockTileEntity(x, y, z)).getStackInSlot(0).stackTagCompound.setByte("x"+reader.readInt()+"y"+reader.readInt()+"z"+reader.readInt(), reader.readByte());;
	}
	
	public void setPixel(int x,int y,int z, int xa,int ya,int za, byte b){
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		DataOutputStream ds = new DataOutputStream(bs);
		try{
			ds.write(x);
			ds.write(y);
			ds.write(z);
			ds.write(xa);
			ds.write(ya);
			ds.write(za);
			ds.write(b);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}

}

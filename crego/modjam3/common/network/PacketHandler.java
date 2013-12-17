package crego.modjam3.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import crego.modjam3.common.info.ModInfo;
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
		if (((IInventory) p.worldObj.getBlockTileEntity(x, y, z))
				.getStackInSlot(0) != null) {
			if (((IInventory) p.worldObj.getBlockTileEntity(x, y, z))
					.getStackInSlot(0).stackTagCompound == null)
				((IInventory) p.worldObj.getBlockTileEntity(x, y, z))
						.getStackInSlot(0).stackTagCompound = new NBTTagCompound();
			((IInventory) p.worldObj.getBlockTileEntity(x, y, z))
					.getStackInSlot(0).stackTagCompound.setByte(
					"x" + reader.readInt() + "y" + reader.readInt() + "z"
							+ reader.readInt(), reader.readByte());
		}

	}

	public static void setPixel(int x, int y, int z, int xa, int ya, int za,
			byte b) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		DataOutputStream ds = new DataOutputStream(bs);
		try {
			ds.writeInt(x);
			ds.writeInt(y);
			ds.writeInt(z);
			ds.writeInt(xa);
			ds.writeInt(ya);
			ds.writeInt(za);
			ds.writeByte(b);
			PacketDispatcher.sendPacketToServer(PacketDispatcher.getPacket(
					ModInfo.CHANAL, bs.toByteArray()));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}

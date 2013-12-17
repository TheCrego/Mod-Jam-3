package crego.modjam3.common.blocks;

import cpw.mods.fml.common.network.FMLNetworkHandler;
import crego.modjam3.common.CraftedTools;
import crego.modjam3.common.info.ModInfo;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCTAnvil extends BlockContainer{

	protected BlockCTAnvil(int par1) {
		super(par1, Material.iron);
		this.setHardness(1);
		this.setResistance(1);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileCTAnvil();
	}
	public void registerIcons(IconRegister par1IconRegister)
    {
		this.blockIcon = par1IconRegister.registerIcon(ModInfo.MODID + ":none");
    }
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	@Override
	public int getRenderType()
    {
        return -1;
    }
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if(l == 2||l==0)
        	par1World.setBlockMetadataWithNotify(x, y, z, 1, 2);
    }
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, CraftedTools.instance, 0, world, x, y, z);
		}
		return true;
	}

}

package crego.modjam3.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class ModelCT extends ModelBase {

	ModelRenderer renderer;

	public ModelCT(NBTTagCompound com,byte b) {
		if (com != null) {
			renderer = new ModelRenderer(this);
			renderer.setTextureSize(64, 64);
			for(int x = -8;x<8;x++)
				for(int y = -8;y<8;y++)
					for(int z = -8;z<8;z++){
						if(com.getByte("x"+x+"y"+y+"z"+z) == b)
							renderer.addBox(x - 0.5F, y - 0.5F, z - 0.5F, 1, 1, 1);
			}
		}
	}
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		if(renderer!=null)
		renderer.render(par7);
	}

}

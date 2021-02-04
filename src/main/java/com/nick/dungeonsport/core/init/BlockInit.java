package com.nick.dungeonsport.core.init;

import com.nick.dungeonsport.DungeonsPort;
import com.nick.dungeonsport.DungeonsPort;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            DungeonsPort.MOD_ID);

    public static final RegistryObject<Block> DIRTY_STONE = BLOCKS.register("dirty_stone",
            () -> new Block((AbstractBlock.Properties.create(Material.CLAY).
                    hardnessAndResistance(1.5F).
                    harvestTool(ToolType.SHOVEL).
                    harvestLevel(-1).
                    sound(SoundType.GROUND))));
}

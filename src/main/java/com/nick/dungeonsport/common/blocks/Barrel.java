package com.nick.dungeonsport.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Barrel extends BaseHorizontalBlock{



    public Barrel(Properties properties) {
        super(properties);
        runCalculation(Stream.of(
                Block.makeCuboidShape(1.5, 0, 1.5, 14.5, 16, 14.5),
                Block.makeCuboidShape(0, 2, 0, 1, 14, 16),
                Block.makeCuboidShape(1, 2, 15, 15, 14, 16),
                Block.makeCuboidShape(15, 2, 0, 16, 14, 16),
                Block.makeCuboidShape(1, 2, 0, 15, 14, 1)
                ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get());
    }



    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPES.get(state.get(HORIZONTAL_FACING));
    }
}

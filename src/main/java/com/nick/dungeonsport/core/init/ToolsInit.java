package com.nick.dungeonsport.core.init;

import com.nick.dungeonsport.DungeonsPort;
import com.nick.dungeonsport.DungeonsPort;
import com.nick.dungeonsport.common.items.FireAxe;
import com.nick.dungeonsport.core.enums.ItemTiers;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ToolsInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            DungeonsPort.MOD_ID);

    public static final RegistryObject<AxeItem> AXE = ITEMS.register("axe",
            () -> new AxeItem(ItemTiers.BASE_AXE,1, -3, new Item.Properties().group(ItemInit.ModItemGroup.instance).setNoRepair()));




}


package com.nick.dungeonsport.core.init;

import com.nick.dungeonsport.DungeonsPort;
import com.nick.dungeonsport.common.items.*;
import com.nick.dungeonsport.core.enums.ItemTiers;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    //public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, DungeonsPort.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            DungeonsPort.MOD_ID);

    //Items
    public static final RegistryObject<Item> KEY = ITEMS.register("key",
            () -> new Item(new Item.Properties().group(ModItemGroup.instance).isImmuneToFire().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ENCHANTED_SHARD = ITEMS.register("enchanted_shard",
            () -> new Item(new Item.Properties().group(ModItemGroup.instance).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<FireAxe> FIREBRAND = ITEMS.register("firebrand",
            () -> new FireAxe(ItemTiers.FIERY_IRON,1, -3, new Item.Properties().group(ItemInit.ModItemGroup.instance).setNoRepair()));

    public static final RegistryObject<Highland_Axe> HIGHLAND_AXE = ITEMS.register("highland_axe",
            () -> new Highland_Axe(ItemTiers.HIGHLAND_AXE, 1, -2.75F, new Item.Properties().group(ItemInit.ModItemGroup.instance).setNoRepair()));

    public static final RegistryObject<Battlestaff_of_terror> BATTLESTAFF_OF_TERROR = ITEMS.register("battlestaff_of_terror",
            () -> new Battlestaff_of_terror(ItemTiers.BATTLESTAFF_OF_TERROR, 1.5F, -2.75F, new Item.Properties().group(ItemInit.ModItemGroup.instance).setNoRepair()));

    public static final RegistryObject<Growing_staff> GROWING_STAFF = ITEMS.register("growing_staff",
            () -> new Growing_staff(ItemTiers.GROWING_STAFF, 1.5F, -2.75F, new Item.Properties().group(ItemInit.ModItemGroup.instance).setNoRepair()));

    public static final RegistryObject<Broadsword> BROADSWORD = ITEMS.register("broadsword",
            () -> new Broadsword(ItemTiers.BROADSWORD, 1F, -2, new Item.Properties().group(ModItemGroup.instance).setNoRepair()));

    public static final RegistryObject<Claymore> CLAYMORE = ITEMS.register("claymore",
            () -> new Claymore(ItemTiers.CLAYMORE, 1F, -2, new Item.Properties().group(ModItemGroup.instance).setNoRepair()));

    //Block Items
    public static final RegistryObject<BlockItem> DIRTY_STONE = ITEMS.register("dirty_stone",
            () -> new BlockItem(BlockInit.DIRTY_STONE.get(), new Item.Properties().group(ModItemGroup.instance)));

    public static final RegistryObject<BlockItem> BARREL = ITEMS.register("barrel",
            () -> new BlockItem(BlockInit.BARREL.get(), new Item.Properties().group(ModItemGroup.instance)));


    //Group
    public static class ModItemGroup extends ItemGroup {
        public static final ModItemGroup instance = new ModItemGroup(ItemGroup.GROUPS.length, "dungeonsport");
        private ModItemGroup(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(KEY.get());
        }

    }
}



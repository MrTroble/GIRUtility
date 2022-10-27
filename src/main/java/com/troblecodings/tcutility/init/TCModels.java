package com.troblecodings.tcutility.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TCModels {

    @SubscribeEvent
    public static void register(final ModelRegistryEvent event) {
        for (int i = 0; i < TCBlocks.blocksToRegister.size(); i++) {
            registerModel(Item.getItemFromBlock(TCBlocks.blocksToRegister.get(i)));
        }

        for (int j = 0; j < TCItems.itemsToRegister.size(); j++) {
            registerModel(TCItems.itemsToRegister.get(j));
        }
    }

    private static void registerModel(final Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}
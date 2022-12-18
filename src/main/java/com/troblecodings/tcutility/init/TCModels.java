package com.troblecodings.tcutility.init;

import com.troblecodings.tcutility.BlockProperties;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public final class TCModels {

    private TCModels() {
    }

    @SubscribeEvent
    public static void register(final ModelRegistryEvent event) {
        for (int i = 0; i < TCBlocks.blocksToRegister.size(); i++) {
            registerModel(Item.getItemFromBlock(TCBlocks.blocksToRegister.get(i)));
        }

        for (int j = 0; j < BlockProperties.jsonBlocksToRegister.size(); j++) {
            registerModel(Item.getItemFromBlock(BlockProperties.jsonBlocksToRegister.get(j)));
        }

        for (int k = 0; k < TCItems.itemsToRegister.size(); k++) {
            registerModel(TCItems.itemsToRegister.get(k));
        }
    }

    private static void registerModel(final Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,
                new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

}

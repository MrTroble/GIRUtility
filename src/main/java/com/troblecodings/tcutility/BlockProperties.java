package com.troblecodings.tcutility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.troblecodings.tcutility.blocks.BlockCreateInfo;
import com.troblecodings.tcutility.blocks.DefaultBlock;
import com.troblecodings.tcutility.blocks.Stairs;
import com.troblecodings.tcutility.blocks.Wall;
import com.troblecodings.tcutility.utils.FileReader;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockProperties {

    private float hardness;
    private String material;
    private String soundtype;
    private int opacity;
    private List<String> states;

    public static final HashMap<String, Material> materialTable = translateTableMaterial();
    public static final HashMap<String, SoundType> soundTable = translateTableSoundType();

    public static ArrayList<Block> jsonBlocksToRegister = new ArrayList<>();

    public static HashMap<String, Material> translateTableMaterial() {
        final HashMap<String, Material> translateTable = new HashMap<>();
        translateTable.put("grass", Material.GRASS);
        translateTable.put("ground", Material.GROUND);
        translateTable.put("wood", Material.WOOD);
        translateTable.put("rock", Material.ROCK);
        translateTable.put("iron", Material.IRON);
        translateTable.put("cloth", Material.CLOTH);
        translateTable.put("sand", Material.SAND);
        translateTable.put("glass", Material.GLASS);
        translateTable.put("ice", Material.ICE);
        translateTable.put("packed_ice", Material.PACKED_ICE);
        translateTable.put("snow", Material.SNOW);
        translateTable.put("clay", Material.CLAY);
        return translateTable;
    }

    public static HashMap<String, SoundType> translateTableSoundType() {
        final HashMap<String, SoundType> translateTable = new HashMap<>();
        translateTable.put("wood", SoundType.WOOD);
        translateTable.put("ground", SoundType.GROUND);
        translateTable.put("stone", SoundType.STONE);
        translateTable.put("metal", SoundType.METAL);
        translateTable.put("glass", SoundType.GLASS);
        translateTable.put("cloth", SoundType.CLOTH);
        translateTable.put("sand", SoundType.SAND);
        translateTable.put("snow", SoundType.SNOW);
        translateTable.put("ladder", SoundType.LADDER);
        translateTable.put("slime", SoundType.SLIME);
        return translateTable;
    }

    public BlockCreateInfo getBlockInfo() {
        final Material mat = materialTable.get(material);
        final SoundType sound = soundTable.get(soundtype);
        if (mat == null) {
            TCUtilityMain.LOG.error("The given material name [%s] is not valid.", material);
            return null;
        }
        if (sound == null) {
            TCUtilityMain.LOG.error("The given sound type [%s] is not valid.", soundtype);
            return null;
        };
        return new BlockCreateInfo(mat, hardness, sound, opacity);
    }

    public void init() {
        final Map<String, BlockProperties> blocks = FileReader
                .getFromJson("/assets/tcutility/blockdefinitions");

        System.out.println(blocks);
        for (final Entry<String, BlockProperties> blocksEntry : blocks.entrySet()) {
            final String objectname = blocksEntry.getKey();

            System.out.println(objectname);
            
            System.out.println(blocksEntry.getValue());

            final BlockProperties property = blocksEntry.getValue();

            final BlockCreateInfo blockInfo = property.getBlockInfo();

            for (final String state : states) {
                switch (state) {
                    case "stair":
                        final DefaultBlock defaultBlock = new DefaultBlock(blockInfo);
                        final Stairs stair = new Stairs(defaultBlock.getDefaultState());
                        stair.setRegistryName(
                                new ResourceLocation(TCUtilityMain.MODID, objectname + "stair"));
                        stair.setUnlocalizedName(objectname + "stair");
                        jsonBlocksToRegister.add(stair);
                        // case "slab":
                        // final Slab slab = new Slab(blockInfo);
                        // supplier evtl ja Defunctional
                        // Interfaces Funktionen mit (Eingabe
                        // und) Ausgabe Parameter
                    case "wall":
                        final Wall wall = new Wall(blockInfo);
                        wall.setRegistryName(
                                new ResourceLocation(TCUtilityMain.MODID, objectname + "wall"));
                        wall.setUnlocalizedName(objectname + "wall");
                        jsonBlocksToRegister.add(wall);
                    default:
                        TCUtilityMain.LOG.error("The given state [%s] is not valid.", state);
                        break;
                }
            }
        }
    }
}

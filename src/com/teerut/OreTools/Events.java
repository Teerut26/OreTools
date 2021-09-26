package com.teerut.OreTools;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener {
    private ArrayList<Block> block_list = new ArrayList<Block>();
    private ArrayList<Material> blockAllow = new ArrayList<Material>();
    private ArrayList<Material> itemAllow = new ArrayList<Material>();

    public void addBlockAllow() {
        blockAllow.add(Material.GOLD_ORE);
        blockAllow.add(Material.IRON_ORE);
        blockAllow.add(Material.COAL_ORE);
        blockAllow.add(Material.DIAMOND_ORE);
        blockAllow.add(Material.LAPIS_ORE);
        blockAllow.add(Material.EMERALD_ORE);
        blockAllow.add(Material.DIORITE);
        blockAllow.add(Material.GRANITE);
        blockAllow.add(Material.ANDESITE);
        blockAllow.add(Material.COPPER_ORE);
        blockAllow.add(Material.TUFF);
        blockAllow.add(Material.REDSTONE_ORE);
        blockAllow.add(Material.DEEPSLATE_COAL_ORE);
        blockAllow.add(Material.DEEPSLATE_COPPER_ORE);
        blockAllow.add(Material.DEEPSLATE_DIAMOND_ORE);
        blockAllow.add(Material.DEEPSLATE_EMERALD_ORE);
        blockAllow.add(Material.DEEPSLATE_GOLD_ORE);
        blockAllow.add(Material.DEEPSLATE_IRON_ORE);
        blockAllow.add(Material.DEEPSLATE_LAPIS_ORE);
        blockAllow.add(Material.DEEPSLATE_REDSTONE_ORE);
        blockAllow.add(Material.NETHER_GOLD_ORE);
        blockAllow.add(Material.NETHER_QUARTZ_ORE);
        blockAllow.add(Material.STONE);
        blockAllow.add(Material.GRAVEL);
        blockAllow.add(Material.SAND);
    }

    public void addItemAllow() {
        itemAllow.add(Material.WOODEN_PICKAXE);
        itemAllow.add(Material.STONE_PICKAXE);
        itemAllow.add(Material.IRON_PICKAXE);
        itemAllow.add(Material.GOLDEN_PICKAXE);
        itemAllow.add(Material.DIAMOND_PICKAXE);
        itemAllow.add(Material.NETHERITE_PICKAXE);
    }



    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        addBlockAllow();
        addItemAllow();
        ItemStack itemStack = (ItemStack) event.getPlayer().getInventory().getItemInMainHand();
        if (blockAllow.contains(event.getBlock().getType()) && itemAllow.contains(itemStack.getType()) && event.getPlayer().isSneaking()) {
            if ((itemStack.getType().getMaxDurability() - itemStack.getDurability()) > 0) {
                Block block = (Block) event.getBlock();
                SearchBlockToArrayFirst(block);
                if (this.block_list.size() > 0) {
                    for (Block block2 : this.block_list) {
                        if (blockAllow.contains(block2.getType())) {
                            block2.breakNaturally(itemStack);
                            itemStack.setDurability((short) (itemStack.getDurability()+1));
                        }

                    }
                }
            }else{
                Block block = (Block) event.getBlock();
                SearchBlockToArrayFirst(block);
                if (this.block_list.size() > 0) {
                    for (Block block2 : this.block_list) {
                        if (blockAllow.contains(block2.getType())) {
                            block2.breakNaturally(itemStack);
                            itemStack.setDurability((short) (itemStack.getDurability()+1));
                        }

                    }
                }
                event.getPlayer().getInventory().remove(itemStack);
                event.getPlayer().playSound(event.getPlayer().getLocation(),Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
            }

        }

    }

    public ArrayList<Block> getBlockList() {
        return this.block_list;
    }

    public void SearchBlockToArrayFirst(Block block) {
        int start = -2;
        int end = 2;
        for (int x = start; x <= end; x++) {
            for (int y = start; y <= end; y++) {
                for (int z = start; z <= end; z++) {
                    Block block2 = block.getRelative(x, y, z);
                    if (block2.getType() == block.getType() && !this.block_list.contains(block2)
                            && blockAllow.contains(block.getType())) {
                        this.block_list.add(block2);
                    }
                }
            }

        }

    }

}

package com.abstractphil.absitem.tools;

import com.abstractphil.absitem.enums.AbsNBTKeys;
import com.redmancometh.reditems.RedItems;
import com.redmancometh.reditems.mediator.AttachmentManager;
import com.redmancometh.reditems.mediator.EnchantManager;
import com.redmancometh.reditems.mediator.NBTManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AbsItemUtil {
    // Simple shortener.
    public static boolean isRedItem(ItemStack item) {
        return(em().isRedItem(item));
    }
    public static boolean isAbsItem(ItemStack item) {
        return isRedItem(item) &&
                nbt().getTag(item).isPresent() &&
                nbt().getTag(item).get().hasKey(AbsNBTKeys.ABS_KEY.getKey());
    }

    public static EnchantManager em() {
        return RedItems.getInstance().getEnchantManager();
    }
    public static AttachmentManager am() {
        return RedItems.getInstance().getAttachManager();
    }
    public static NBTManager nbt() {
        return RedItems.getInstance().getNbtManager();
    }

    public static ItemStack buildSkullItem(Material m, String itemName, String skullOwner, String... lore) {
        ItemStack i = new ItemStack(m, 1, (short)3);
        SkullMeta meta = (SkullMeta)i.getItemMeta();
        meta.setOwner(skullOwner);
        meta.setDisplayName(itemName);
        meta.setLore(Arrays.asList(lore));
        i.setItemMeta(meta);
        return i;
    }

    // Redman code.
    public static boolean isChestplate(ItemStack item) {
        switch (item.getType()) {
            case IRON_CHESTPLATE:
                return true;
            case GOLD_CHESTPLATE:
                return true;
            case DIAMOND_CHESTPLATE:
                return true;
            default:
                return false;
        }
    }
    // Redman code.
    public static boolean isLegs(ItemStack item) {
        switch (item.getType()) {
            case IRON_LEGGINGS:
                return true;
            case GOLD_LEGGINGS:
                return true;
            case DIAMOND_LEGGINGS:
                return true;
            default:
                return false;
        }
    }

    // Redman code.
    public static boolean isBoots(ItemStack item) {
        switch (item.getType()) {
            case IRON_BOOTS:
                return true;
            case GOLD_BOOTS:
                return true;
            case DIAMOND_BOOTS:
                return true;
            default:
                return false;
        }
    }

    // Redman code.
    public static boolean isHelmet(ItemStack item) {
        switch (item.getType()) {
            case IRON_HELMET:
                return true;
            case GOLD_HELMET:
                return true;
            case DIAMOND_HELMET:
                return true;
            default:
                return false;
        }
    }
}

package com.abstractphil.absitem.mediators;

import com.abstractphil.absitem.effects.AbsEffect;
import com.abstractphil.absitem.effects.AbsItem;
import com.abstractphil.absitem.tools.AbsItemUtil;
import com.redmancometh.reditems.abstraction.Effect;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

@Data
public class AbsPlaceholderOptions {
    enum PLACEHOLDER_LIMITERS {
        PLAYER("player", 0),
        RED_ITEM( "rItem", 1),
        ABS_ITEM("aItem", 2),
        INVENTORY("pInventory", 3),
        SHOP("genericGui", 4),
        RED_EFFECT("rEffect", 5),
        ABS_EFFECT("aEffect", 6);

        private final String key;
        private final int value;

        PLACEHOLDER_LIMITERS(String keyIn, int valueIn) {
            this.key = keyIn;
            this.value = valueIn;
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

    }

    private List<Object> objectList;

    @Nullable
    public Player getPlayer() {
        for(Object obj : objectList) { if(obj instanceof Player) return (Player)obj; }
        return null;
    }
    @Nullable
    public ItemStack getRedItem() {
        for(Object obj : objectList) {
            if(obj instanceof ItemStack && AbsItemUtil.isRedItem((ItemStack)obj)) return (ItemStack)obj;
        }
        return null;
    }
    @Nullable
    public ItemStack getAbsItem() {
        for(Object obj : objectList) {
            if (obj instanceof ItemStack && AbsItemUtil.isAbsItem((ItemStack) obj)) return (ItemStack) obj;
        }
        return null;
    }
    @Nullable
    public Inventory getInventory() {
        if(getPlayer() != null) {
            for(Object obj : objectList) {
                if (obj instanceof Inventory &&
                        getPlayer() != null &&
                        getPlayer().getInventory() == obj) {
                    return (Inventory) obj;
                }
            }
        }
        return null;
    }
    public Inventory getGenericGUI() {
        if(getPlayer() != null) {
            for(Object obj : objectList) {
                if (obj instanceof Inventory &&
                        getPlayer() != null &&
                        getPlayer().getInventory() != obj)
                    return (Inventory) obj;
            }
        }
        return null;
    }

    public AbsPlaceholderOptions(List<Object> objectListIn) {
        objectList = objectListIn;
    }

}

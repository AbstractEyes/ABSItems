package com.abstractphil.absitem.tools;

import com.abstractphil.absitem.cfg.AbsEffectData;
import com.abstractphil.absitem.cfg.AbsItemData;
import com.abstractphil.absitem.effects.AbsEffect;
import com.abstractphil.absitem.effects.AbsItem;
import com.abstractphil.absitem.enums.AbsNBTKeys;
import com.redmancometh.reditems.abstraction.Effect;
import com.redmancometh.reditems.storage.EnchantData;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

// Helper methods to make leveling simpler. Uses specific hard coded values for exp and level.
// ItemStackNBT: effectName:level
//    The attached effect level.
// ItemStackNBT: effectName:exp
//    The attached effect exp.
// ItemStackNBT: item:level
//    The item level.
// ItemStackNBT: item:totallevel
//    The total amount of effect levels.
// ItemStackNBT: item:exp
//    The item exp.
public class AbsLevelUtil {
    public static ItemStack prepareAbsNBT(ItemStack item) {
        NBTTagCompound comp = NBTUtil.getNBTTags(item);
        if(comp == null) item = NBTUtil.setNBTDefault(item);
        comp = NBTUtil.getNBTTags(item);
        assert comp != null; comp.setInt(AbsNBTKeys.ABS_KEY.getKey(), AbsNBTKeys.ABS_KEY.getValue());
        return NBTUtil.setNBTData(item, comp);
    }

    public static boolean canLevelEffectComplex(ItemStack item, AbsEffectData effectData) {
        int level;
        int expToLevel;
        int totalExp;
    }
    private static ItemStack addTotalLevel(ItemStack item, int levelGain) {
    }
    private static ItemStack setTotalLevel(ItemStack item, int totalLevel) {

    }
    public static ItemStack getTotalLevel(ItemStack item) {

    }

    // Adds to the current effect's level.
    public static ItemStack addEffectLevel(ItemStack item, AbsEffect effectData, int levelGain) {
        item = NBTUtil.setData(item, effectData.getKey() + ":level",
                levelGain + getEffectLevel(item, effectData));
        return item;
    }

    // Manually sets the effect level, replaces current value.
    public static ItemStack setEffectLevel(ItemStack item, AbsEffect effectData, int level, boolean replaceExp) {
        item = NBTUtil.setData(item, effectData.getKey() + ":level", level);
        if(replaceExp) item = NBTUtil.setData(item, effectData.getKey() + ":exp", level);
        return item;
    }

    // Adds a new effect level if one isn't present.
    public static ItemStack setupEffectLevel(ItemStack item, AbsEffect effectData, int startingLevel) {
        if(!NBTUtil.hasData(item, effectData.getKey() + ":level"))
            item = NBTUtil.setData(item, effectData.getKey() + ":level", startingLevel);
        return item;
    }
    public static ItemStack setupEffectExp(ItemStack item, AbsEffect effectData, int startingExp) {
        if(!NBTUtil.hasData(item, effectData.getKey() + ":exp"))
            item = NBTUtil.setData(item, effectData.getKey() + ":exp", startingExp);
        return item;
    }
    // Completely removes the level data.
    //  Many options include disenchant, and this would be stray data otherwise.
    public static ItemStack removeEffectLevel(ItemStack item, AbsEffect effectData) {
        if(!NBTUtil.hasData(item, effectData.getKey() + ":level"))
            item = NBTUtil.removeData(item, effectData.getKey() + ":level");
        return item;
    }

    // Item level up checks and functions.
    public static ItemStack addEffectExp(ItemStack item, AbsEffect effectData, int expGain) {
        item = NBTUtil.setData(item, effectData.getKey() + ":exp",
                expGain + getEffectLevel(item, effectData));
        return item;
    }


    // Adds a level to said item.
    public static ItemStack addItemLevel(ItemStack item, AbsEffect itemData, int level) {
        return item;
    }
    // Manually sets the item level.
    public static ItemStack setItemLevel(ItemStack item, AbsEffect itemData, int level) {
        return item;
    }
    // Removes the item level data.
    public static ItemStack removeItemLevel(ItemStack item, AbsEffect itemData) {
        return item;
    }

    // Gets the maximum possible item level.
    @Nullable
    public static Integer maxItemLevel(AbsItemData absItemData) {
        if(absItemData == null) return null;
        return absItemData.getMaxTotalLevel();
    }
    // Gets the maximum amount of concurrent effects from item data.
    @Nullable
    public static Integer maxItemEffects(AbsItemData absItemData) {
        if(absItemData == null) return null;
        return absItemData.getMaximumEffects();
    }
    // Gets the maximum level of an effect.
    @Nullable
    public static Integer maxEffectLevel(Effect effectData) {
        if(effectData == null) return null;
        return effectData.getMaxNaturalLevel();
    }

    // Determines if the item itself can level.
    public static boolean canItemLevelSimple(ItemStack item, Effect absItemData, int addedLevel) {
        if(absItemData == null || getItemLevel(item) == null) return false;
        if(((AbsItem) absItemData).getMaxTotalLevel() <= 0) return true;
        ArrayList<Effect> excluded = new ArrayList<>();
        excluded.add(absItemData);
        if(((AbsItem) absItemData).getMaxNaturalLevel() >= getItemLevel(item) + addedLevel) {
            return true;
        }
        return false;
    }
    // Determines if the effect can level.
    public static boolean canEffectLevelSimple(ItemStack item, Effect effectData, int levelGain) {
        if(effectData == null || getEffectLevel(item, effectData) == null) return false;
        return getEffectLevel(item, effectData) + levelGain <= maxEffectLevel(effectData);
    }

    // Returns the effect level.
    public static Integer getEffectLevel(ItemStack item, Effect effectData) {
        return NBTUtil.getIntData(item, effectData.getKey() + ":level");
    }
    // Returns the effect EXP.
    public static Integer getEffectExp(ItemStack item, AbsEffectData effectData) {
        return NBTUtil.getIntData(item, effectData.getKey() + ":exp");
    }
    public static Integer getItemLevel(ItemStack item) {
        return NBTUtil.getIntData(item, "item:level");
    }
    public static Integer getItemExp(ItemStack item) {
        return NBTUtil.getIntData(item, "item:exp");
    }
    public static Integer getTotalEffectLevel(ItemStack item) {
        return NBTUtil.getIntData(item, "item:totallevel");
    }

    // Get total effect count with exclusion list with small optimizations.
    public static int getTotalEffectCount(ItemStack item, List<Effect> excluded) {
        int count = 0;
        for (EnchantData effect : NBTUtil.em().getEffects(item)) {
            Effect excl = null;
            for (Effect exclude : excluded) {
                if (exclude.getKey().equals(effect.getEffect().getKey())) {
                    excl = exclude;
                    break;
                }
            }
            if(excl == null) {
                count += 1;
            } else {
                excluded.remove(excl);
            }
        }
        return count;
    }

    public static int getTotalEffectLevel(ItemStack item, List<Effect> excluded) {
        int count = 0;
        for (EnchantData effect : NBTUtil.em().getEffects(item)) {
            Effect excl = null;
            for (Effect exclude : excluded) {
                if (exclude.getKey().equals(effect.getEffect().getKey())) {
                    excl = exclude;
                    break;
                }
            }
            if(excl != null) {
                count += getEffectLevel(item, effect.getEffect());
            } else {
                excluded.remove(excl);
            }
        }
        return count;
    }

}

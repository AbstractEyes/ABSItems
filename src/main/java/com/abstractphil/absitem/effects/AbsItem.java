package com.abstractphil.absitem.effects;

import com.abstractphil.absitem.cfg.AbsItemData;
import com.abstractphil.absitem.mediators.AbsItemManager;
import com.redmancometh.reditems.EnchantType;
import com.redmancometh.warcore.util.Pair;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Function;

public class AbsItem extends AbsEffectClass {
    @Getter
    AbsItemData data;
    AbsItemManager manager;
    public void setData(AbsItemData dataIn) {
        data = dataIn;
    }
    public void setManager(AbsItemManager managerIn) {
        manager = managerIn;
    }

    @Override
    public List<String> getLore() {
        return data.getDisplayLore();
    }

    @Override
    public String getName() {
        return data.getKey();
    }
    public String getDisplayName() {
        return data.getDisplayName();
    }
    public String getDisplayName(ItemStack item) {
        return item.getItemMeta().getDisplayName();
    }

    // Todo: implement static lookup for NBT tags.

    @Override
    public EnchantType getType() {
        return null;
    }

    @Override
    public int getMaxNaturalLevel() {
        return data.getMaxLevel();
    }

    public int getMaximumEffects() {
        return data.getMaximumEffects();
    }

    public int getMaxTotalLevel() {
        return data.getMaxTotalLevel();
    }

    @Override
    public String getKey() {
        return super.getKey();
    }
    @Override
    public List<Pair<String, Function<Integer, String>>> placeholders() {
        return super.placeholders();
    }
    @Override
    public Pair<Boolean, String> hasBuffType(ItemStack item) {
        return super.hasBuffType(item);
    }
    @Override
    public boolean applicableFor(ItemStack item) {
        return super.applicableFor(item);
    }
}

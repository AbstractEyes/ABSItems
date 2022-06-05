package com.abstractphil.absitem.effects;

import com.abstractphil.absitem.cfg.AbsEffectData;
import com.redmancometh.reditems.EnchantType;

import java.util.List;

public class AbsEffectClass implements AbsEffect {
    private AbsEffectData data;
    public void setData(AbsEffectData dataIn) { data = dataIn; }
    public AbsEffectData getData() { return data; }

    @Override
    public List<String> getLore() {
        return data.getDisplayLore();
    }

    public List<String> getDisplayLore() {
        return getLore();
    }

    public List<String> getGuiLore() {
        return data.getGuiLore();
    }

    public Integer getExpPerLevel(int level) {
        return data.getBaseExpToLevel();
    }

    public double getExpIncPerLevel() {
        return data.getExpPctIncPerLvl();
    }

    public String getVanillaEnchant() {
        return data.getVanillaEnchant();
    }

    @Override
    public String getName() {
        return data.getKey();
    }

    @Override
    public EnchantType getType() {
        return null;
    }

    @Override
    public int getMaxNaturalLevel() {
        return data.getMaxLevel();
    }


}

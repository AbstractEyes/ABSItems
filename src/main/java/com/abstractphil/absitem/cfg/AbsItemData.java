package com.abstractphil.absitem.cfg;

import com.abstractphil.absitem.effects.AbsItem;
import lombok.Data;
import org.bukkit.Material;

import java.util.List;

@Data
public class AbsItemData extends AbsLevelData {
    private Class<? extends AbsItem> clazz;
    private String key, displayName, skullOwner, skullMetaData;
    private List<String> guiLore, displayLore;
    private Material material;
    private int maximumEffects;
    private int maxTotalLevel;
}

package com.abstractphil.absitem.cfg;

import java.util.ArrayList;
import java.util.List;

import com.abstractphil.absitem.effects.AbsEffectClass;
import lombok.Data;
import org.bukkit.Material;

@Data
public class AbsEffectData extends AbsLevelData {
	private Class<? extends AbsEffectClass> clazz;
	private String key, guiName, displayName, vanillaEnchant;
	private ArrayList<String> guiLore, displayLore;
	private Material material;
	private String skullHost;
}

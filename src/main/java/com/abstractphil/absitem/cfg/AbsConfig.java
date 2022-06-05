package com.abstractphil.absitem.cfg;

import java.util.HashMap;
import java.util.Map;

import com.sun.tools.javac.util.List;
import lombok.Data;

@Data
public class AbsConfig {
	private HashMap<String, AbsItemData> items;
	private HashMap<String, AbsEffectData> effects;
	private HashMap<String, String> placeholders;
	private HashMap<String, String> nbtKeyChains;
	private HashMap<String, String> jsonKeyChains;
}
